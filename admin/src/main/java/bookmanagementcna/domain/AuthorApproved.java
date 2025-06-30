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
<<<<<<< HEAD
=======

    public void publishAfterCommit() {
        System.out.println("AuthorApproved Event published: " + this);
    }
>>>>>>> 095c5db1600412671eb330388e095fbf60263f0a
}
//>>> DDD / Domain Event
