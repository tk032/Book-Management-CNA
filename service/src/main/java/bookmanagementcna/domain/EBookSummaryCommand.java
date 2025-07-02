package bookmanagementcna.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EBookSummaryCommand {
    private Long bookId;
    private String bookContent;
}
