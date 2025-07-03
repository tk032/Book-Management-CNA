package bookmanagementcna.dto;

import lombok.Data;

@Data
public class LogoutRequest {
    private Long userId;
    private String email;
    private String name;
}
