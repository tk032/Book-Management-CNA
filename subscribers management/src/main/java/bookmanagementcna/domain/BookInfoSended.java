package bookmanagementcna.domain;

import bookmanagementcna.infra.AbstractEvent;
import lombok.Data;
import lombok.ToString;
import java.util.List;

@Data
@ToString
public class BookInfoSended extends AbstractEvent {
    private Long id;
    private Long publicationId;
    private Long authorId;
    private String title;
    private String summaryText;
    private String coverImageUrl;
    private String address;
    private Boolean ktCustomer;
    private String password;
    private List<String> recipientEmails; 
    private Long bookId;
}
