package bookmanagementcna.domain;

import lombok.Data;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class ViewCount {

    @Id
    private Long bookId;

    private Integer count;

    private String title;
    private String authorName;
    private String summaryText;
    private String coverImageUrl;
    private Date publishedDate;
}
