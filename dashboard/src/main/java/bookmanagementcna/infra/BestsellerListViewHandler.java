package bookmanagementcna.infra;

import bookmanagementcna.config.kafka.KafkaProcessor;
import bookmanagementcna.domain.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class BestsellerListViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private BestsellerListRepository bestsellerListRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenBestsellerIdentified_then_CREATE_1(
        @Payload BestsellerIdentified bestsellerIdentified
    ) {
        try {
            if (!bestsellerIdentified.validate()) return;

            // view 객체 생성
            BestsellerList bestsellerList = new BestsellerList();
            // view 객체에 이벤트의 Value 를 set 함
            bestsellerList.setId(bestsellerIdentified.getId());
            bestsellerList.setTitle(bestsellerIdentified.getTitle());
            bestsellerList.setAuthor(bestsellerIdentified.getAuthorName());
            bestsellerList.setContentSummary(
                bestsellerIdentified.getSummaryText()
            );
            bestsellerList.setCoverUrl(bestsellerIdentified.getCoverImageUrl());
            bestsellerList.setPublishedDate(
                String.valueOf(bestsellerIdentified.getPublishedDate())
            );
            // view 레파지 토리에 save
            bestsellerListRepository.save(bestsellerList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
