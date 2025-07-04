package bookmanagementcna.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private String contentSummary;
    private String coverUrl;
    private Date publishedDate;
    private Boolean isAvailable;

}
