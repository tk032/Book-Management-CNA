package bookmanagementcna.infra;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import bookmanagementcna.domain.BookSummaryCompleted;

@Component

public class BookSummaryPolicyHandler {
    @TransactionalEventListener
    public void handleBookSummaryCompleted(BookSummaryCompleted event) {
        System.out.println("요약 완료 이벤트 수신: " + event.getSummary());
        // DB 저장, 피드 업데이트 등 후처리
    }
    
}
