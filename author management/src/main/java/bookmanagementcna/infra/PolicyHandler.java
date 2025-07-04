package bookmanagementcna.infra;

import bookmanagementcna.config.kafka.KafkaProcessor;
import bookmanagementcna.domain.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    PublishManageRepository publishManageRepository;

    @Autowired
    AICoverImageGenerator aiCoverImageGenerator;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='AuthorApproved'"
    )
    public void wheneverAuthorApproved_ApproveAuthor(
        @Payload AuthorApproved authorApproved
    ) {
        AuthorApproved event = authorApproved;
        System.out.println(
            "\n\n##### listener ApproveAuthor : " + authorApproved + "\n\n"
        );

        // Sample Logic //
        Author.approveAuthor(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BookApproved'"
    )
    public void wheneverBookApproved_ApprovePublish(
        @Payload BookApproved bookApproved
    ) {
        BookApproved event = bookApproved;
        System.out.println(
            "\n\n##### listener ApprovePublish : " + bookApproved + "\n\n"
        );

        // Sample Logic //
        PublishManage.approvePublish(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PublishRequestRegistered'"
    )
    public void wheneverPublishRequested_GenerateAICover(
        @Payload PublishRequestRegistered event
    ) {
        System.out.println(
            "\n\n🎨 listener GenerateAICover : " + event + "\n\n"
        );

        try {
            String title = event.getBookTitle();
            String summary = event.getBookContent();
            String keyword = event.getAuthorName();

            String coverImageUrl = aiCoverImageGenerator.generateCover(title, summary, keyword);
            System.out.println("✅ 커버 이미지 생성 완료: " + coverImageUrl);

            // 선택: DB에 저장하거나 후속 이벤트 발행 가능
            // 예: publishManageRepository.findById(event.getId()) 해서 저장 가능

        } catch (Exception e) {
            System.out.println("❌ AI 커버 생성 실패: " + e.getMessage());
        }
    }
}
//>>> Clean Arch / Inbound Adaptor
