package bookmanagementcna.domain;

import bookmanagementcna.domain.ReportResolved;
import bookmanagementcna.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class ReportResolved extends AbstractEvent {

    private Long id;
    private Long requestId;
    private String requestType;
    private String targetId;
    private String requestedAt;
    private String status;
    private Long adminId;
    private Date approvedAt;
    private String message;
}
