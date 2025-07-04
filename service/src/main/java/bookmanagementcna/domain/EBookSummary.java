package bookmanagementcna.domain;

import bookmanagementcna.domain.EBookSummary;
import bookmanagementcna.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class EBookSummary extends AbstractEvent {

    private Long id;
    private Long publicationId;
    private String summaryText;
    private Date generatedAt;
    private String status;

    public EBookSummary(EBookService aggregate) {
        super(aggregate);
    }

    public EBookSummary() {
        super();
    }
}