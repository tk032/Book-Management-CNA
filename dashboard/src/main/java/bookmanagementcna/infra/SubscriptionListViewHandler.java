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
public class SubscriptionListViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private SubscriptionListRepository subscriptionListRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenLogin_then_CREATE_1(@Payload Login login) {
        try {
            if (!login.validate()) return;

            // view 객체 생성
            SubscriptionList subscriber = new SubscriptionList();
            // view 객체에 이벤트의 Value 를 set 함
            subscriber.setId(login.getId());
            subscriber.setName(login.getName());
            //subscriber.setEmail(login.getEmail());
            subscriber.setPoint(login.getPoint());
            subscriber.setJoinStatus(login.getJoinStatus());

            // view 레파지 토리에 save
            subscriptionListRepository.save(subscriber);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whenContentViewEnabled_then_UPDATE_1(
        @Payload ContentViewEnabled contentViewEnabled
    ) {
        try {
            if (!contentViewEnabled.validate()) return;

            // 1. 해당 뷰 객체 존재 여부 확인
            Optional<SubscriptionList> optional = subscriptionListRepository.findById(contentViewEnabled.getId());

            if (optional.isPresent()) {
                // 업데이트
                SubscriptionList subscriber = optional.get();
                subscriber.setName(contentViewEnabled.getName());
                subscriber.setJoinStatus(contentViewEnabled.getJoinStatus());
                //subscriber.setPoint(contentViewEnabled.getPoint());
                subscriptionListRepository.save(subscriber);

            } else {
                // 없으면 예외 또는 로그 출력만 (생성 X)
                System.err.println("[ContentViewEnabled 처리 실패] 해당 ID의 SubscriptionList가 존재하지 않음: ID = " + contentViewEnabled.getId());
                // 또는 예외 던져서 롤백 처리하고 싶다면:
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
