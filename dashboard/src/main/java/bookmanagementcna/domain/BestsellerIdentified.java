package bookmanagementcna.domain;

import bookmanagementcna.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class BestsellerIdentified extends AbstractEvent {

    private Long id;
    private Long publicationId;
    private String isBestSeller;
    private Date selectedAt;
    private String message;
    private Long authorId;
    private String title;
    private String summaryText;
    private String coverImageUrl;
    private String productRegistered;
    private Boolean isPublishCompleted;
    private String status;
    private Date publishedDate;
    private String authorName;
}
