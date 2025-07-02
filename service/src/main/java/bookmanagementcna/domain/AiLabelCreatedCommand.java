package bookmanagementcna.domain;

import lombok.Data;

@Data
public class AiLabelCreatedCommand {
    private String title;
    private String description;
    private Long bookId;
}
