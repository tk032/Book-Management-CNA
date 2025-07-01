package bookmanagementcna.application;

import bookmanagementcna.domain.AI표지생성됨;
import bookmanagementcna.domain.AI표지이미지생성Command;
import bookmanagementcna.external.AIImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AI표지이미지생성Handler {

    @Autowired
    AIImageService aiImageService;

    public void handle(AI표지이미지생성Command command) {
        String imageUrl = aiImageService.generateCover(command.getTitle(), command.getDescription());

        AI표지생성됨 event = new AI표지생성됨();
        event.setImageUrl(imageUrl);
        event.setBookId(command.getBookId());
        event.publishAfterCommit();
    }
}
