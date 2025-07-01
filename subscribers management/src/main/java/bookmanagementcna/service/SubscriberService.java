package bookmanagementcna.service;

import bookmanagementcna.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubscriberService {

    @Autowired
    private SubscriberRepository subscriberRepository;

    // 회원가입
    @Transactional
    public Subscriber register(Subscriber subscriber) {
        return subscriberRepository.save(subscriber);
    }

    // 로그인
    @Transactional
    public void login(String email) {
        Subscriber subscriber = subscriberRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));
        subscriber.login();
        subscriberRepository.save(subscriber);
    }

    // 로그아웃
    @Transactional
    public void logout(String email) {
        Subscriber subscriber = subscriberRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));
        subscriber.logout();
        subscriberRepository.save(subscriber);
    }

    // 책 열람 (포인트 차감 또는 무제한 열람)
    @Transactional
    public void readContent(Long subscriberId, int point) throws Subscriber.InsufficientPointsException {
        Subscriber subscriber = subscriberRepository.findById(subscriberId)
            .orElseThrow(() -> new RuntimeException("회원이 없습니다."));
        subscriber.usePointForContent(point);
        subscriberRepository.save(subscriber);
    }

    // 포인트 충전
    @Transactional
    public void addPoints(Long subscriberId, int amount) {
        Subscriber subscriber = subscriberRepository.findById(subscriberId)
            .orElseThrow(() -> new RuntimeException("회원이 없습니다."));
        subscriber.addPoints(amount);
        subscriberRepository.save(subscriber);
    }

    // 요금제 가입
    @Transactional
    public void subscribeUnlimitedPlan(Long subscriberId) {
        Subscriber subscriber = subscriberRepository.findById(subscriberId)
            .orElseThrow(() -> new RuntimeException("회원이 없습니다."));
        subscriber.subscribeUnlimitedPlan();
        subscriberRepository.save(subscriber);
    }

    // 요금제 해지
    @Transactional
    public void unsubscribeUnlimitedPlan(Long subscriberId) {
        Subscriber subscriber = subscriberRepository.findById(subscriberId)
            .orElseThrow(() -> new RuntimeException("회원이 없습니다."));
        subscriber.unsubscribeUnlimitedPlan();
        subscriberRepository.save(subscriber);
    }
}
