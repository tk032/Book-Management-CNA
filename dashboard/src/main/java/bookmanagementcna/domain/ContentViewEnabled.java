package bookmanagementcna.domain;

import bookmanagementcna.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class ContentViewEnabled extends AbstractEvent {

    private Long id;
    private String email;
    private String name;
    private Boolean joinStatus;
}
