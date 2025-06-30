package bookmanagementcna.domain;

import bookmanagementcna.domain.*;
import bookmanagementcna.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class AuthorApproved extends AbstractEvent {

    private Long id;
    private String requestId;
    private String requestType;
    private Long targetId;
    private String requestedAt;
    private String status;
    private Long adminId;
    private Date approvedAt;
    private String message;

    public AuthorApproved(Admin aggregate) {
        super(aggregate);
        this.requestId = aggregate.getRequestId();
        this.targetId = aggregate.getTargetId();
    }

    public AuthorApproved() {
        super();
    }

    public void publishAfterCommit() {
        System.out.println("AuthorApproved Event published: " + this);
    }
}
//>>> DDD / Domain Event