package bookmanagementcna.domain;

import bookmanagementcna.domain.*;
import bookmanagementcna.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class BookApproved extends AbstractEvent {

    private Long id;
    private Long requestId;
    private String requestType;
    private String targetId;
    private String requestedAt;
    private String status;
    private Long adminId;
    private Date approvedAt;
    private String message;

    public BookApproved(Admin aggregate) {
        super(aggregate);
        this.requestId = aggregate.getRequestId();
        this.targetId = aggregate.getRequestId();
    }

    public BookApproved() {
        super();
    }
<<<<<<< HEAD
=======

    public void publishAfterCommit() {
        System.out.println("BookApproved Event published: " + this);
    }
>>>>>>> 095c5db1600412671eb330388e095fbf60263f0a
}
//>>> DDD / Domain Event
