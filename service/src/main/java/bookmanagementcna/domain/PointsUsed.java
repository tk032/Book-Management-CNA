package bookmanagementcna.domain;

import bookmanagementcna.domain.PointsUsed;
import bookmanagementcna.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class PointsUsed extends AbstractEvent {

    private Long id;
    private String email;
    private String name;
    private Integer point;
    private Boolean joinStatus;
}
