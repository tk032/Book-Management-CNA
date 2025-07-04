package bookmanagementcna.domain;

import lombok.Data;

@Data
public class BookSummaryCompleted {
    private Long subscriberId;
    private String bookTitle;
    private String summary;
}
