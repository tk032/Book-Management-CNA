package bookmanagementcna.domain;

import bookmanagementcna.infra.AbstractEvent;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
public class AiLabelCreated extends AbstractEvent {

    private Long bookId;
    private String title;
    private String description;

    public AiLabelCreated(EBookService aggregate) {
        super(aggregate);
    }

    public AiLabelCreated(Long bookId, String title, String description) {
        super();
        this.bookId = bookId;
        this.title = title;
        this.description = description;
    }
}
