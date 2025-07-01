package bookmanagementcna.domain;

import bookmanagementcna.domain.*;
import bookmanagementcna.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

//<<< DDD / Domain Event
@Data
@ToString
public class RequestApproved extends AbstractEvent {

    private Long id;
    private Long authorId;
    private String bookTitle;
    private String bookContent;
    private String publishStatus;
    private String authorName;

    public RequestApproved(PublishManage aggregate) {
        super(aggregate);
        BeanUtils.copyProperties(aggregate, this);
    }

    public RequestApproved() {
        super();
    }
}
//>>> DDD / Domain Event
