package bookmanagementcna.policy;

import bookmanagementcna.domain.*;
import bookmanagementcna.infra.KafkaProcessor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class BookInfoPolicy {

    @StreamListener(value = KafkaProcessor.INPUT, condition = "headers['type']=='PointsUsed'")
    public void wheneverPointsUsed_thenBookRequest(@Payload PointsUsed event) {
        if (event == null) return;
        System.out.println(" Policy Triggered: PointsUsed → BookRequest");

        Service.bookRequest(event);       // 어그리거트 정적 메서드 호출
    }
}
