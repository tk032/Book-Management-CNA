package bookmanagementcna.domain;

import bookmanagementcna.infra.AbstractEvent;
import lombok.Data;
import lombok.ToString;
import java.util.Date;

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
    private String address;
    private Boolean ktCustomer;
    private String password;
    private String userEmail;
    private Long bookId;
}
