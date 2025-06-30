package bookmanagementcna.domain;

import bookmanagementcna.ServiceApplication;
import bookmanagementcna.domain.AiLabelCreated;
import bookmanagementcna.domain.BestsellerIdentified;
import bookmanagementcna.domain.BookInfoSended;
import bookmanagementcna.domain.BookPublicated;
import bookmanagementcna.domain.EBookSummary;
import bookmanagementcna.domain.ProductAutomaticallyRegistered;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Service_table")
@Data
//<<< DDD / Aggregate Root
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long publicationId;

    private String authorName;

    private Long authorId;

    private String title;

    private String summaryText;

    private String coverImageUrl;

    private String productRegistered;

    private Boolean isPublishCompleted;

    private String isBestSeller;

    private String status;

    private Date publishedDate;

    private String message;

    @PostPersist
    public void onPostPersist() {
        ProductAutomaticallyRegistered productAutomaticallyRegistered = new ProductAutomaticallyRegistered(
            this
        );
        productAutomaticallyRegistered.publishAfterCommit();

        BookPublicated bookPublicated = new BookPublicated(this);
        bookPublicated.publishAfterCommit();

        BestsellerIdentified bestsellerIdentified = new BestsellerIdentified(
            this
        );
        bestsellerIdentified.publishAfterCommit();

        BookInfoSended bookInfoSended = new BookInfoSended(this);
        bookInfoSended.publishAfterCommit();

        EBookSummary eBookSummary = new EBookSummary(this);
        eBookSummary.publishAfterCommit();

        AiLabelCreated aiLabelCreated = new AiLabelCreated(this);
        aiLabelCreated.publishAfterCommit();
    }

    public static ServiceRepository repository() {
        ServiceRepository serviceRepository = ServiceApplication.applicationContext.getBean(
            ServiceRepository.class
        );
        return serviceRepository;
    }

    @Entity
public class Service {
    @Id
    private Long id;
    private String publicationId;
    private String title;
    private Boolean isBestSeller;
    private Boolean isPublishCompleted;

    public void publishRequest(PublishCommand cmd) {
        this.isPublishCompleted = true;
        ServicePublished event = new ServicePublished(this);
        event.publishAfterCommit(); // 1. 이벤트 발행
    }
  }
}