package bookmanagementcna.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BestsellerDto {
    private Long id;
    private String title;
    private String author;
    private String contentSummary;
    private String coverUrl;
    private String publishedDate;
}
