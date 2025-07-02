package bookmanagementcna.domain;

import bookmanagementcna.domain.*;
import bookmanagementcna.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class BookPublicated extends AbstractEvent {

    private Long id;
    private Long publicationId;
    private Date publishedDate;
    private String status;
    private Boolean isPublishCompleted;
    private Long authorId;
    private String title;
    private String summaryText;
    private String coverImageUrl;
    private String message;
    private String userEmail;
    private Long bookId;
}
