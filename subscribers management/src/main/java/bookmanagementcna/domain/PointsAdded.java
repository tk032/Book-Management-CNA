package bookmanagementcna.domain;

import bookmanagementcna.domain.*;
import bookmanagementcna.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class PointsAdded extends AbstractEvent {

    private Long id;
    private String email;
    private String name;
    private Integer point;
    private Boolean ktCustomer;
    private String region;       // 지역 정보
    private String password; 

    public PointsAdded(Subscriber aggregate) {
        super(aggregate);
    }

    public PointsAdded() {
        super();
    }
}
//>>> DDD / Domain Event
