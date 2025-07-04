package bookmanagementcna.domain;

import bookmanagementcna.domain.AICoverImage;
import bookmanagementcna.infra.AbstractEvent;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
public class AICoverImage extends AbstractEvent {

    private Long bookId;
    private String title;
    private String description;

    public AICoverImage(EBookService aggregate) {
        super(aggregate);
    }

    public AICoverImage(Long bookId, String title, String description) {
        super();
        this.bookId = bookId;
        this.title = title;
        this.description = description;
    }
}
