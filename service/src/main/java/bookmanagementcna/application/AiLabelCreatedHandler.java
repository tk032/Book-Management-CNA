package bookmanagementcna.application;

import bookmanagementcna.domain.AiLabelCreatedCommand;
import bookmanagementcna.domain.AiLabelCreated;
import bookmanagementcna.external.AICoverImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AiLabelCreatedHandler {

    @Autowired
    private AICoverImageService aiCoverImageService;

    public void handle(AiLabelCreatedCommand command) {
        // GPT 기반 이미지 생성 API 호출
        String imageUrl = aiCoverImageService.generate(command.getTitle(), command.getDescription());

        event = new AiLabelCreated();
        event.setBookId(command.getBookId());
        event.setImageUrl(imageUrl);
        event.publishAfterCommit();
    }
}
