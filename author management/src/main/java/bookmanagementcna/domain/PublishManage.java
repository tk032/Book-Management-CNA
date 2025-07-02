package bookmanagementcna.domain;
 
import bookmanagementcna.AuthorManagementApplication;
import bookmanagementcna.domain.PublishRequestRegistered;
import bookmanagementcna.domain.RequestApproved;
import bookmanagementcna.domain.SaveCompleted;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
 
@Entity
@Table(name = "PublishManage_table")
@Data
//<<< DDD / Aggregate Root
public class PublishManage {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    private String authorName;
    private Long authorId;
    private String bookTitle;
    private String bookContent;
    private Boolean finalSave;
    private String publishStatus;
 
    public void requestPublication() {
        this.publishStatus = "REQUESTED";
    }
 
    public void approve() {
        this.publishStatus = "APPROVED";
    }
 
    public void reject() {
        this.publishStatus = "REJECTED";
    }
 
    @PostPersist
    public void onPostPersist() {
        if ("REQUESTED".equals(this.publishStatus)) {
            PublishRequestRegistered event = new PublishRequestRegistered(this);
            event.publishAfterCommit();
        }
 
        if (Boolean.TRUE.equals(this.finalSave)) {
            SaveCompleted event = new SaveCompleted(this);
            event.publishAfterCommit();
        }
    }
 
    public static PublishManageRepository repository() {
        return AuthorManagementApplication.applicationContext.getBean(PublishManageRepository.class);
    }
 
    //<<< Clean Arch / Port Method
    public static void approvePublish(BookApproved bookApproved) {
        try {
            Long id = Long.valueOf(bookApproved.getTargetId());
 
            repository().findById(id).ifPresent(publishManage -> {
                publishManage.approve(); // 상태 변경: "APPROVED"
                repository().save(publishManage);
 
                RequestApproved event = new RequestApproved(publishManage);
                event.publishAfterCommit();
            });
 
        } catch (NumberFormatException e) {
            System.out.println("Invalid targetId in BookApproved: " + bookApproved.getTargetId());
        }
    }
    //>>> Clean Arch / Port Method
 
}
//>>> DDD / Aggregate Root
