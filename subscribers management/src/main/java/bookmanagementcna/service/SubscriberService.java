package bookmanagementcna.service;

import bookmanagementcna.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class SubscriberService {

    @Autowired
    private SubscriberRepository subscriberRepository;
    
    @Autowired
    private StreamBridge streamBridge;

    @Autowired
    private PasswordEncoder passwordEncoder;   


    // 회원가입
    @Transactional
    public Subscriber register(Subscriber subscriber) {
        if (subscriberRepository.findByEmail(subscriber.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다: " + subscriber.getEmail());
        }

        // 비밀번호 암호화
        subscriber.setPassword(passwordEncoder.encode(subscriber.getPassword()));
        subscriber.setUnlimitedPlan(false);
        subscriber.setKtCustomer(false); //회원가입시 kt 인증 기본값
        Subscriber saved = subscriberRepository.save(subscriber);

        streamBridge.send("event-out", 
            "회원가입: " + saved.getEmail() + 
            ", 초기 포인트: " + saved.getPoint() +
            ", 주소: " + saved.getAddress() // 새 필드 반영
        );

        return saved;
    }

    // 로그인
    @Transactional
    public Subscriber login(String email, String password) { // 반환 타입 변경!
        // 1. 회원 존재 확인
        Subscriber subscriber = subscriberRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        // 2. 안전한 비밀번호 비교
        String storedPassword = subscriber.getPassword();
        if (storedPassword == null || !passwordEncoder.matches(password, storedPassword)) {
            throw new RuntimeException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        // 3. 로그인 처리
        subscriber.login();
        subscriberRepository.save(subscriber);

        // Login 이벤트 객체 생성
        bookmanagementcna.domain.Login loginEvent = new bookmanagementcna.domain.Login();
        loginEvent.setId(subscriber.getId());
        loginEvent.setEmail(subscriber.getEmail());
        loginEvent.setName(subscriber.getName());
        loginEvent.setMessage("로그인 성공");
        loginEvent.setPoint(subscriber.getPoint());
        loginEvent.setJoinStatus(subscriber.getJoinStatus());
        loginEvent.setKtCustomer(subscriber.getKtCustomer());
        loginEvent.setLoginStatus("ACTIVE");
        // loginEvent.setPassword(null); // 민감 정보는 보내지 않도록
        // loginEvent.setRegion(null);   // 필요 시 설정

        // Kafka로 JSON 전송
        streamBridge.send("event-out", loginEvent);
    }


        return subscriber; // 로그인 성공한 회원 객체 반환!
    }


    // 로그아웃
    @Transactional
    public void logout(String email) {
        Subscriber subscriber = subscriberRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));
        subscriber.logout();
        subscriberRepository.save(subscriber);
        
        streamBridge.send("event-out", 
            "로그아웃: " + email + 
            ", 남은 포인트: " + subscriber.getPoint()
        );
    }

    // 책 열람 (요금제 상태에 따라 포인트 차감 여부 결정)
    @Transactional
    public void readContent(Long subscriberId, int point) throws Subscriber.InsufficientPointsException {
        Subscriber subscriber = subscriberRepository.findById(subscriberId)
            .orElseThrow(() -> new RuntimeException("회원이 없습니다."));
        
        int beforePoints = subscriber.getPoint() != null ? subscriber.getPoint() : 0;
        boolean isUnlimited = Boolean.TRUE.equals(subscriber.getUnlimitedPlan());
        
        String pointMessage;
        if (isUnlimited) {
            pointMessage = " (요금제 가입 상태: 포인트 미차감)";
        } else {
            subscriber.usePointForContent(point);
            pointMessage = ", 사용 포인트: " + point;
        }
        
        subscriberRepository.save(subscriber);
        
        streamBridge.send("event-out", 
            "책 열람: 회원ID " + subscriberId + 
            pointMessage +
            ", 남은 포인트: " + subscriber.getPoint() +
            " (변경 전: " + beforePoints + ")" +
            ", 요금제 상태: " + (isUnlimited ? "가입" : "미가입")
        );
    }

    // 포인트 충전 (null-safe 처리)
    @Transactional
    public void addPoints(Long subscriberId, int amount) {
        Subscriber subscriber = subscriberRepository.findById(subscriberId)
            .orElseThrow(() -> new RuntimeException("회원이 없습니다."));
        
        int beforePoints = subscriber.getPoint() != null ? subscriber.getPoint() : 0;
        subscriber.addPoints(amount);
        subscriberRepository.save(subscriber);
        
        streamBridge.send("event-out", 
            "포인트 충전: 회원ID " + subscriberId + 
            ", 충전량: " + amount + 
            ", 남은 포인트: " + subscriber.getPoint() +
            " (변경 전: " + beforePoints + ")"
        );
    }

    // 요금제 가입
    @Transactional
    public void subscribeUnlimitedPlan(Long subscriberId) {
        Subscriber subscriber = subscriberRepository.findById(subscriberId)
            .orElseThrow(() -> new RuntimeException("회원이 없습니다."));
        
        boolean beforeStatus = subscriber.getUnlimitedPlan();
        subscriber.subscribeUnlimitedPlan();
        subscriberRepository.save(subscriber);
        
        streamBridge.send("event-out", 
            "요금제 가입: 회원ID " + subscriberId + 
            ", 현재 상태: " + subscriber.getUnlimitedPlan() +
            " (변경 전: " + beforeStatus + ")" +
            ", 남은 포인트: " + subscriber.getPoint()
        );
    }

    // 요금제 해지
    @Transactional
    public void unsubscribeUnlimitedPlan(Long subscriberId) {
        Subscriber subscriber = subscriberRepository.findById(subscriberId)
            .orElseThrow(() -> new RuntimeException("회원이 없습니다."));
        
        boolean beforeStatus = subscriber.getUnlimitedPlan();
        subscriber.unsubscribeUnlimitedPlan();
        subscriberRepository.save(subscriber);
        
        streamBridge.send("event-out", 
            "요금제 해지: 회원ID " + subscriberId + 
            ", 현재 상태: " + subscriber.getUnlimitedPlan() +
            " (변경 전: " + beforeStatus + ")" +
            ", 남은 포인트: " + subscriber.getPoint()
        );
    }

    // kt 인증
    @Transactional
    public void authenticateKtCustomer(Long id) {
        Subscriber subscriber = subscriberRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("회원이 없습니다."));

        if (Boolean.TRUE.equals(subscriber.getKtCustomer())) {
            throw new IllegalStateException("이미 KT 고객 인증 및 포인트 지급이 완료되었습니다.");
        }

        // 인증 및 포인트 지급
        subscriber.setKtCustomer(true);
        subscriber.setPoint(subscriber.getPoint() + 2000); // 예시: 2000점 지급
        subscriberRepository.save(subscriber);
    }
}
