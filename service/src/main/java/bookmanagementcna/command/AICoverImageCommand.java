package bookmanagementcna.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AICoverImageCommand {
    private Long publicationId;
    private String title;
    private String description;
}
