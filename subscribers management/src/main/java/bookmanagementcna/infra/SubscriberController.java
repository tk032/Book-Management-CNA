package bookmanagementcna.infra;

import bookmanagementcna.domain.Subscriber;
import bookmanagementcna.service.SubscriberService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/subscribers")
public class SubscriberController {

    @Autowired
    private SubscriberService subscriberService;

    @Autowired
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<SubscriberResponse> register(@Valid @RequestBody SignupRequest request) {
        Subscriber subscriber = new Subscriber();
        subscriber.setEmail(request.getEmail());
        subscriber.setPassword(passwordEncoder.encode(request.getPassword())); // 비밀번호 해싱
        subscriber.setName(request.getName());
        subscriber.setAddress(request.getAddress());
        subscriber.setJoinStatus(true);
        subscriber.setPoint(1000); // 가입 포인트 지급

        Subscriber saved = subscriberService.register(subscriber);

        // 응답에서 password 등 민감 정보 제외
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

    // 로그인 (예시)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // 실제 로그인 로직은 JWT, 세션 등과 연동 필요
        subscriberService.login(request.getEmail());
        return ResponseEntity.ok().build();
    }

    // 로그아웃 (예시)
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
}
