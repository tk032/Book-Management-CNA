package bookmanagementcna.domain;

import bookmanagementcna.domain.*;
import bookmanagementcna.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

<<<<<<< HEAD
//<<< DDD / Domain Event
=======
//<<< DDD / Domain Event // 출간됨, 전자책 요약됨
>>>>>>> 095c5db1600412671eb330388e095fbf60263f0a
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

    public BookPublicated(Service aggregate) {
        super(aggregate);
    }

    public BookPublicated() {
        super();
    }
}
<<<<<<< HEAD
//>>> DDD / Domain Event
=======


>>>>>>> 095c5db1600412671eb330388e095fbf60263f0a
