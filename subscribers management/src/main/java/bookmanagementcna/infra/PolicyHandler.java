package bookmanagementcna.infra;

import bookmanagementcna.config.kafka.KafkaProcessor;
import bookmanagementcna.domain.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    private static final Logger logger = LoggerFactory.getLogger(PolicyHandler.class);

    @Autowired
    SubscriberRepository subscriberRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BookPublicated'"
    )
    public void wheneverBookPublicated_UpdateFeedWhenBookInfoTransferred(
        @Payload BookPublicated event
    ) {
        logger.info("##### listener: BookPublicated {}", event);

        if (event.getUserEmail() == null) {
            logger.error("Missing userEmail in event: {}", event);
            return;
        }

        subscriberRepository.findByEmail(event.getUserEmail()).ifPresent(subscriber -> {
            // 도서 ID와 제목으로 피드 업데이트
            subscriber.updateBookFeed(event.getBookId(), event.getTitle());
            subscriberRepository.save(subscriber);
            logger.info("Updated feed for: {}", event.getUserEmail());
        });
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BookInfoSended'"
    )
    public void wheneverBookInfoSended_BookInfoSave(
        @Payload BookInfoSended event
    ) {
        logger.info("##### listener: BookInfoSended {}", event);

        if (event.getRecipientEmails() == null || event.getBookId() == null) {
            logger.error("Invalid event data: {}", event);
            return;
        }

        event.getRecipientEmails().forEach(email -> {
            subscriberRepository.findByEmail(email).ifPresent(subscriber -> {
                subscriber.addRecommendedBook(event.getBookId());
                subscriberRepository.save(subscriber);
                logger.info("Added recommendation for: {}", email);
            });
        });
    }

}

//>>> Clean Arch / Inbound Adaptor
