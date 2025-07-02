package bookmanagementcna.application;

import bookmanagementcna.domain.AiLabelCreatedCommand;
import bookmanagementcna.domain.AiLabelCreated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class AiLabelCreatedHandler {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void handle(AiLabelCreatedCommand command) {

        AiLabelCreated event = new AiLabelCreated();
        event.setBookId(command.getBookId());
        event.setTitle(command.getTitle());
        event.setDescription(command.getDescription());

        eventPublisher.publishEvent(event);
    }
}
