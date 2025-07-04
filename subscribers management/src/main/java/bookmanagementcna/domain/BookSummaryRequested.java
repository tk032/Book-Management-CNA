package bookmanagementcna.domain;

import lombok.Data;

@Data
public class BookSummaryRequested {
    private Long subscriberId;
    private String bookTitle;
    private String bookContent;
    
}
