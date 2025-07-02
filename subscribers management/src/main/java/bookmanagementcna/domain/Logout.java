package bookmanagementcna.domain;

import bookmanagementcna.domain.*;
import bookmanagementcna.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
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
    private String region;       // 지역 정보
    private String password; 

    public Logout(Subscriber aggregate) {
        super(aggregate);
    }

    public Logout() {
        super();
    }
}
//>>> DDD / Domain Event
