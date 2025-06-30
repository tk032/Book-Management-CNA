package bookmanagementcna.infra;

import bookmanagementcna.domain.*;
import bookmanagementcna.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;

//<<< Clean Arch / Inbound Adaptor
@RestController
@RequestMapping("/api/subscribers") // 기본 경로 추가
@Transactional
public class SubscriberController {

    @Autowired
    private SubscriberService subscriberService; // 서비스 주입

    // 회원가입
    @PostMapping("/register")
    public Subscriber register(@RequestBody Subscriber subscriber) {
        return subscriberService.register(subscriber);
    }

    // 로그인
    @PostMapping("/login")
    public void login(@RequestParam String email) {
        subscriberService.login(email);
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
//>>> Clean Arch / Inbound Adaptor
