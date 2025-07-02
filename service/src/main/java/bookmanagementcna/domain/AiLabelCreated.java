package bookmanagementcna.domain;

import bookmanagementcna.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class AiLabelCreated extends AbstractEvent {

    private Long id;
    private Long publicationId;
    private Date generatedAt;
    private String status;

    public AiLabelCreated(EBookService aggregate) {
        super(aggregate);
    }

    public AiLabelCreated() {
        super();
    }
}