package bookmanagementcna.domain;

import bookmanagementcna.domain.*;
import bookmanagementcna.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class Logout extends AbstractEvent {

    private Long id;
    private String email;
    private String name;
    private String message;
    private Integer point;
    private Boolean joinStatus;
    private Boolean ktCustomer;
    private String loginStatus;

    public Logout(Admin aggregate) {
        super(aggregate);
        this.id = aggregate.getId();
    }

    public Logout() {
        super();
    }

    public Long getUserId() {
        return this.id;
    }

    public String getMessage(String msg) {
        message = msg;
        return message;
    }
}
