package bookmanagementcna.domain;

import bookmanagementcna.SubscribersManagementApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Subscriber_table")
@Data
//<<< DDD / Aggregate Root
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String email;
    private String name;
    private String message;
    private Integer point;
    private Boolean joinStatus;
    private Boolean ktCustomer;
    private String loginStatus;
    private String region;       // 지역 정보
    private String password;     // 비밀번호

    // [추가] 요금제 가입 여부 필드
    private Boolean unlimitedPlan = false; // 기본값: 미가입 상태

    // [추가] 도메인 이벤트 임시 저장소 (영속성 대상 아님)
    @Transient
    private final List<Object> domainEvents = new ArrayList<>();

    // [추가] 도메인 이벤트 등록 메서드
    public void registerEvent(Object event) {
        this.domainEvents.add(event);
    }

    // [추가] 도메인 이벤트 조회 메서드
    public List<Object> getDomainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    // [추가] 도메인 이벤트 비우기
    public void clearEvents() {
        domainEvents.clear();
    }

    @PrePersist
    public void onPrePersist() {
        if (this.point == null) {
            this.point = 1000; // 가입 기본 포인트 설정
        }
    }

    @PostPersist
    public void onPostPersist() {
        // 회원가입 시점에만 필요한 이벤트만 등록
        SignedIn signedIn = new SignedIn(this);
        registerEvent(signedIn);
        
        // 포인트 지급 이벤트 등록 (값은 이미 설정됨)
        PointsAdded pointsAdded = new PointsAdded(this);
        registerEvent(pointsAdded);
    }

    // 로그인
    public void login() {
        this.loginStatus = "LOGIN";
        Login login = new Login(this);
        registerEvent(login);
    }

    // 로그아웃
    public void logout() {
        this.loginStatus = "LOGOUT";
        Logout logout = new Logout(this);
        registerEvent(logout);
    }

    // 콘텐츠 열람 (요금제 여부 확인 및 포인트 부족 예외처리)
    public void usePointForContent(int amount) throws InsufficientPointsException {
        if (Boolean.TRUE.equals(this.unlimitedPlan)) {
            // 무제한 요금제 가입자는 포인트 차감 없이 바로 열람
            ContentViewEnabled contentViewEnabled = new ContentViewEnabled(this);
            registerEvent(contentViewEnabled);
            return;
        }
        if (this.point == null || this.point < amount) {
            throw new InsufficientPointsException(
                "포인트가 " + (amount - (this.point != null ? this.point : 0)) + "점 부족해요!"
            );
        }
        this.point -= amount;
        PointsUsed pointsUsed = new PointsUsed(this);
        registerEvent(pointsUsed);
        ContentViewEnabled contentViewEnabled = new ContentViewEnabled(this);
        registerEvent(contentViewEnabled);
    }

    // 포인트 충전
    public void addPoints(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("0 이상의 포인트만 충전 가능해요!");
        }
        this.point = (this.point == null) ? amount : this.point + amount;
        PointsAdded pointsAdded = new PointsAdded(this);
        registerEvent(pointsAdded);
    }

    // 요금제 가입
    public void subscribeUnlimitedPlan() {
        this.unlimitedPlan = true;
        // TODO: 요금제 가입 이벤트 필요시 registerEvent()로 추가
    }

    // 요금제 해지
    public void unsubscribeUnlimitedPlan() {
        this.unlimitedPlan = false;
        // TODO: 요금제 해지 이벤트 필요시 registerEvent()로 추가
    }

    // 포인트 부족 예외 클래스 (내부 정적 클래스)
    public static class InsufficientPointsException extends Exception {
        public InsufficientPointsException(String message) {
            super(message);
        }
    }

    public static SubscriberRepository repository() {
        SubscriberRepository subscriberRepository = SubscribersManagementApplication.applicationContext.getBean(
            SubscriberRepository.class
        );
        return subscriberRepository;
    }

    //<<< Clean Arch / Port Method
    public static void updateFeedWhenBookInfoTransferred(
        BookPublicated bookPublicated
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        Subscriber subscriber = new Subscriber();
        repository().save(subscriber);

        */

        /** Example 2:  finding and process
        
        // if bookPublicated.gptApiId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<, Object> serviceMap = mapper.convertValue(bookPublicated.getGptApiId(), Map.class);

        repository().findById(bookPublicated.get???()).ifPresent(subscriber->{
            
            subscriber // do something
            repository().save(subscriber);

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void bookInfoSave(BookInfoSended bookInfoSended) {
        //implement business logic here:

        /** Example 1:  new item 
        Subscriber subscriber = new Subscriber();
        repository().save(subscriber);

        */

        /** Example 2:  finding and process
        
        // if bookInfoSended.gptApiId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<, Object> serviceMap = mapper.convertValue(bookInfoSended.getGptApiId(), Map.class);

        repository().findById(bookInfoSended.get???()).ifPresent(subscriber->{
            
            subscriber // do something
            repository().save(subscriber);

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
