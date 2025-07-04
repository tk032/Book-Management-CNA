package bookmanagementcna.infra;

import bookmanagementcna.config.kafka.KafkaProcessor;
import bookmanagementcna.domain.EBookService;
import bookmanagementcna.domain.PointsUsed;
import bookmanagementcna.domain.ReportResolved;
import bookmanagementcna.domain.RequestApproved;
import bookmanagementcna.domain.ServiceRepository;
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
    private EBookService eBookService;  // Service -> eBookService 로 수정

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='RequestApproved'"
    )
    public void wheneverRequestApproved_CreateAiCoverImage(
        @Payload RequestApproved requestApproved
    ) {
        System.out.println("\n\n##### listener CreateAiCoverImage : " + requestApproved + "\n\n");

        eBookService.createAiCoverImage(requestApproved);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PointsUsed'"
    )
    public void wheneverPointsUsed_BookRequest(@Payload PointsUsed pointsUsed) {
        System.out.println("\n\n##### listener BookRequest : " + pointsUsed + "\n\n");

        eBookService.bookRequest(pointsUsed);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PointsUsed'"
    )
    public void wheneverPointsUsed_ViewIncrease(@Payload PointsUsed pointsUsed) {
        System.out.println("\n\n##### listener ViewIncrease : " + pointsUsed + "\n\n");

        eBookService.viewIncrease(pointsUsed);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReportResolved'"
    )
    public void wheneverReportResolved_DeactivateContent(@Payload ReportResolved reportResolved) {
        System.out.println("\n\n##### listener DeactivateContent : " + reportResolved + "\n\n");

        eBookService.deactivateContent(reportResolved);
    }
}

//>>> Clean Arch / Inbound Adaptor
