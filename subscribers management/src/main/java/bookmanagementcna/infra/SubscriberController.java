package bookmanagementcna.infra;

import bookmanagementcna.domain.*;
import bookmanagementcna.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/subscribers")
@Transactional
public class SubscriberController {

    @Autowired
    private SubscriberService subscriberService;

    // 회원가입
    @PostMapping("/register")
    public Subscriber register(@RequestBody Subscriber subscriber) {
        return subscriberService.register(subscriber);
    }

    // 로그인
    @PostMapping("/login")
    public void login(@RequestBody LoginRequest loginRequest) {
        subscriberService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }

    // DTO 클래스 (수정됨)
    public static class LoginRequest {
        private String email;
        private String password;

        // 수동 getter 추가
        public String getEmail() { return email; }
        public String getPassword() { return password; }
        
        // setter도 필요한 경우 추가
        public void setEmail(String email) { this.email = email; }
        public void setPassword(String password) { this.password = password; }
    }

    // 로그아웃
    @PostMapping("/logout")
    public void logout(@RequestParam String email) {
        subscriberService.logout(email);
    }

    // 포인트 충전
    @PostMapping("/{id}/addPoints")
    public void addPoints(
        @PathVariable Long id,
        @RequestParam int amount
    ) {
        subscriberService.addPoints(id, amount);
    }

    // 책 열람 (포인트 사용)
    @PostMapping("/{id}/readContent")
    public void readContent(
        @PathVariable Long id,
        @RequestParam int point
    ) throws Subscriber.InsufficientPointsException {
        subscriberService.readContent(id, point);
    }

    // 요금제 가입
    @PostMapping("/{id}/subscribe")
    public void subscribeUnlimitedPlan(@PathVariable Long id) {
        subscriberService.subscribeUnlimitedPlan(id);
    }

    // 요금제 해지
    @PostMapping("/{id}/unsubscribe")
    public void unsubscribeUnlimitedPlan(@PathVariable Long id) {
        subscriberService.unsubscribeUnlimitedPlan(id);
    }
}
