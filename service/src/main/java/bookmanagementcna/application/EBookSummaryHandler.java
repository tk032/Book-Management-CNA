package bookmanagementcna.application;

import bookmanagementcna.domain.EBookServiceRepository;
import bookmanagementcna.domain.EBookSummary;
import bookmanagementcna.domain.EBookSummaryCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EBookSummaryHandler {

    @Autowired
    private EBookServiceRepository eBookServiceRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void handle(EBookSummaryCommand command) {

        // 전자책 요약 로직 예시 (간단히 요약 텍스트 생성)
        String summaryText = "요약: " + command.getContent().substring(0, Math.min(100, command.getContent().length()));

        // 이벤트 생성 및 필드 설정
        EBookSummary event = new EBookSummary();
        event.setPublicationId(command.getBookId());
        event.setSummaryText(summaryText);

        // 이벤트 발행
        eventPublisher.publishEvent(event);
    }
}
