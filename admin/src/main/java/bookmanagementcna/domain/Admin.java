package bookmanagementcna.domain;

import bookmanagementcna.AdminApplication;
import bookmanagementcna.domain.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Admin_table")
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long requestId;
    private String requestType; // AUTHOR, BOOK, REPORT
    private Long targetId;
    private Date requestedAt;
    private String status;      // APPROVED, REJECTED, RESOLVED
    private Long adminId;
    private Date approvedAt;
    private String message;

    @PostPersist
    public void onPostPersist() {
        switch (this.requestType) {
            case "REPORT":
                ReportResolved reportResolved = new ReportResolved(this);
                reportResolved.publishAfterCommit();
                break;
            case "BOOK":
                BookApproved bookApproved = new BookApproved(this);
                bookApproved.publishAfterCommit();
                break;
            // ✅ LOGIN, LOGOUT 은 PolicyHandler에서만 발행
        }
    }

    @PostUpdate
    public void onPostUpdate() {
        if ("AUTHOR".equals(this.requestType)) {
            AuthorApproved authorApproved = new AuthorApproved(this);
            authorApproved.publishAfterCommit();
        }
    }

    public void approveAuthor() {
        this.status = "APPROVED";
        this.approvedAt = new Date();
    }

    public void approveBook() {
        this.status = "APPROVED";
        this.approvedAt = new Date();
    }

    public void resolveReport() {
        this.status = "RESOLVED";
        this.approvedAt = new Date();
    }

    public void approveLogin() {
        this.status = "LOGIN_APPROVED";
        this.approvedAt = new Date();
    }

    public void approveLogout() {
        this.status = "LOGOUT_APPROVED";
        this.approvedAt = new Date();
    }
}
