package bookmanagementcna.dto;

import lombok.Data;

@Data
public class ApproveRequest {
    private Long requestId;
    private Long targetId;
    private Long adminId;
    private String message;
}
