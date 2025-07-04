package bookmanagementcna.service;

import org.springframework.stereotype.Component;
import bookmanagementcna.domain.BookSummaryCompleted;

@Component

public class SubscriberEventPublisher {
        public void publish(BookSummaryCompleted event) {
        // 임시 구현: 콘솔에 출력
        System.out.println("이벤트 발행됨: " + event);
    }
}
