package bookmanagementcna.domain;

import bookmanagementcna.domain.*;
import bookmanagementcna.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ReportResolved extends AbstractEvent {

    private Long id;
    private Long requestId;
    private String requestType;
    private Long targetId;
    private Date requestedAt;
    private String status;
    private Long adminId;
    private Date approvedAt;
    private String message;

    public ReportResolved(Admin aggregate) {
        super(aggregate);
        this.requestId = Long.valueOf(aggregate.getRequestId());
        this.targetId = Long.valueOf(aggregate.getTargetId());
    }

    public ReportResolved() {
        super();
    }

    public void publishAfterCommit() {
        System.out.println("ReportResolved Evenet published: " + this);
    }
}
//>>> DDD / Domain Event