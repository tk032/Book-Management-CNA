package bookmanagementcna.application;

import bookmanagementcna.domain.AICoverImageCommand;
import bookmanagementcna.domain.AICoverImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class AICoverImageHandler {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void handle(AICoverImageCommand command) {

        AICoverImage event = new AICoverImage();
        event.setBookId(command.getBookId());
        event.setTitle(command.getTitle());
        event.setDescription(command.getDescription());

        eventPublisher.publishEvent(event);
    }
}
