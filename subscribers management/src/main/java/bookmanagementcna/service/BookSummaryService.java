package bookmanagementcna.service;

import org.springframework.stereotype.Service;

import bookmanagementcna.domain.BookSummaryCompleted;
import bookmanagementcna.domain.BookSummaryRequested;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class BookSummaryService {
    private final OpenAiClient openAiClient;
    private final SubscriberEventPublisher eventPublisher;

    public void requestBookSummary(BookSummaryRequested request) {
        String summary = openAiClient.generateSummary(request.getBookContent());

        BookSummaryCompleted event = new BookSummaryCompleted();
        event.setSubscriberId(request.getSubscriberId());
        event.setBookTitle(request.getBookTitle());
        event.setSummary(summary);

        eventPublisher.publish(event); // Kafka 또는 EventBus
    }
}
