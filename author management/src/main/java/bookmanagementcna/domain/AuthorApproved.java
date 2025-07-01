package bookmanagementcna.domain;

import bookmanagementcna.domain.*;
import bookmanagementcna.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class AuthorApproved extends AbstractEvent {

    private Long id; // 승인id
    private String requestId;
    private String requestType;
    private String targetId;
    private String requestedAt;
    private String status;
    private String adminId;
    private String approvedAt;
    private String message;

    public AuthorApproved(Author author) {
        super(author);
        this.targetId = String.valueOf(author.getId());
        this.status = author.getRegisterStatus(); // String이라면 그대로 사용
        this.requestType = "AUTHOR_APPROVAL";
    }

    public AuthorApproved() {
        super();
    }

}
