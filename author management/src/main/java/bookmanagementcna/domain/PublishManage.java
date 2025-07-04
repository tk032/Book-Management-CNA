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

    @PostPersist
    public void onPostPersist() {
        SaveCompleted saveCompleted = new SaveCompleted(this);
        saveCompleted.publishAfterCommit();

        RequestApproved requestApproved = new RequestApproved(this);
        requestApproved.publishAfterCommit();

        PublishRequestRegistered publishRequestRegistered = new PublishRequestRegistered(this);
        publishRequestRegistered.publishAfterCommit();
    }

     // ✅ 추가된 부분
    public void requestPublication() {
        this.publishStatus = "REQUESTED";  // 또는 "요청"
    }

    public void approve() {
        this.publishStatus = "APPROVED";   // 또는 "승인됨"
    }

    public static PublishManageRepository repository() {
        PublishManageRepository publishManageRepository =
            AuthorManagementApplication.applicationContext.getBean(PublishManageRepository.class);
        return publishManageRepository;
    }

    //<<< Clean Arch / Port Method
    public static void approvePublish(BookApproved bookApproved) {
        try {
            Long id = Long.valueOf(bookApproved.getTargetId());

            repository().findById(id).ifPresent(publishManage -> {
                publishManage.setPublishStatus("승인됨"); // 상태 변경
                repository().save(publishManage); // 저장

                RequestApproved requestApproved = new RequestApproved(publishManage);
                requestApproved.publishAfterCommit();
            });

        } catch (NumberFormatException e) {
            System.out.println("Invalid targetId in BookApproved: " + bookApproved.getTargetId());
        }
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root


