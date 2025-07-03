package bookmanagementcna.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private Long userId;
    private String email;
    private String name;
}
