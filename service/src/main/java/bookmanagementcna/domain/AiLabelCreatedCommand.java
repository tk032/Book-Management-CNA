package bookmanagementcna.domain;

import lombok.Data;

@Data
public class AiLabelCreatedCommand {
    private Long publicationId;
    private String label;
}