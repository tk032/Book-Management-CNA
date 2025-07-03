package bookmanagementcna.infra;

import bookmanagementcna.domain.Subscriber;
import bookmanagementcna.service.SubscriberService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.transaction.annotation.Transactional; // 추가
import org.springframework.security.crypto.password.PasswordEncoder; // 추가
import javax.validation.Valid;

// ... (생략)

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/subscribers")
@Transactional
public class SubscriberController {

    @Autowired
    private SubscriberService subscriberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<SubscriberResponse> register(@Valid @RequestBody SignupRequest request) {
        Subscriber subscriber = new Subscriber();
        subscriber.setEmail(request.getEmail());
        subscriber.setPassword(request.getPassword());
        subscriber.setName(request.getName());
        subscriber.setAddress(request.getAddress());
        subscriber.setJoinStatus(true);
        subscriber.setPoint(1000);

        Subscriber saved = subscriberService.register(subscriber);

        SubscriberResponse response = new SubscriberResponse(
            saved.getId(),
            saved.getEmail(),
            saved.getName(),
            saved.getAddress(),
            saved.getPoint(),
            saved.getJoinStatus()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // 로그인 성공 시 Subscriber 반환하도록 서비스 수정 필요
            Subscriber subscriber = subscriberService.login(
                loginRequest.getEmail(),
                loginRequest.getPassword()
            ); // 수정

            LoginResponse response = new LoginResponse( // 추가
                "로그인 성공",                        // 추가
                subscriber.getId(),                  // 추가
                subscriber.getEmail(),               // 추가
                subscriber.getName()                 // 추가
            );                                       // 추가
            return ResponseEntity.ok(response);      // 수정

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody LogoutRequest request) {
        subscriberService.logout(request.getEmail());
        return ResponseEntity.ok().build();
    }

    // 포인트 충전
    @PostMapping("/{id}/addPoints")
    public ResponseEntity<?> addPoints(
        @PathVariable Long id,
        @RequestParam int amount
    ) {
        subscriberService.addPoints(id, amount);
        return ResponseEntity.ok().build();
    }

    // 책 열람 (포인트 사용)
    @PostMapping("/{id}/readContent")
    public ResponseEntity<?> readContent(
        @PathVariable Long id,
        @RequestParam int point
    ) throws Subscriber.InsufficientPointsException {
        subscriberService.readContent(id, point);
        return ResponseEntity.ok().build();
    }

    // 요금제 가입
    @PostMapping("/{id}/subscribe")
    public ResponseEntity<?> subscribeUnlimitedPlan(@PathVariable Long id) {
        subscriberService.subscribeUnlimitedPlan(id);
        return ResponseEntity.ok().build();
    }

    // 요금제 해지
    @PostMapping("/{id}/unsubscribe")
    public ResponseEntity<?> unsubscribeUnlimitedPlan(@PathVariable Long id) {
        subscriberService.unsubscribeUnlimitedPlan(id);
        return ResponseEntity.ok().build();
    }

    //kt 고객 여부
    @PostMapping("/{id}/ktauth")
    public ResponseEntity<?> authenticateKtCustomer(@PathVariable Long id) {
        try {
            subscriberService.authenticateKtCustomer(id);
            return ResponseEntity.ok("KT 고객 인증 및 포인트 지급 완료");
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // --- DTO 클래스들 ---
    @Data
    public static class SignupRequest {
        private String email;
        private String password;
        private String name;
        private String address;
    }

    @Data
    public static class LoginRequest {
        private String email;
        private String password;
    }

    @Data
    public static class LogoutRequest {
        private String email;
    }

    @Data
    public static class SubscriberResponse {
        private Long id;
        private String email;
        private String name;
        private String address;
        private Integer point;
        private Boolean joinStatus;

        public SubscriberResponse(Long id, String email, String name, String address, Integer point, Boolean joinStatus) {
            this.id = id;
            this.email = email;
            this.name = name;
            this.address = address;
            this.point = point;
            this.joinStatus = joinStatus;
        }
    }

    // 아래 LoginResponse DTO 추가 // 추가
    @Data
    public static class LoginResponse { // 추가
        private String message;         // 추가
        private Long id;                // 추가
        private String email;           // 추가
        private String name;            // 추가

        public LoginResponse(String message, Long id, String email, String name) { // 추가
            this.message = message;     // 추가
            this.id = id;               // 추가
            this.email = email;         // 추가
            this.name = name;           // 추가
        }                               // 추가
    }                                   // 추가
}
