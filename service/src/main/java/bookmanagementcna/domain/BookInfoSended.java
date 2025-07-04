package bookmanagementcna.domain;

import bookmanagementcna.domain.BookInfoSended;
import bookmanagementcna.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class BookInfoSended extends AbstractEvent {

    private Long id;
    private Long publicationId;
    private Long authorId;
    private String title;
    private String summaryText;
    private String coverImageUrl;

    public BookInfoSended(EBookService aggregate) {
        super(aggregate);
    }

    public BookInfoSended() {
        super();
    }
}