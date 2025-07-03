package bookmanagementcna.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EBookSummaryCommand {
    private Long bookId;
    private String content;
}
