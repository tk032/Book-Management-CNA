package bookmanagementcna.domain;

import bookmanagementcna.domain.*;
import bookmanagementcna.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

/**
 * 로그인 이벤트
 * 사용자가 로그인할 때 발행되며, 상태 동기화 또는 인증 연동에 사용될 수 있음.
 */


//<<< DDD / Domain Event
@Data
@ToString
public class Login extends AbstractEvent {

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

    public Login(Subscriber aggregate) {
        super(aggregate);
    }

    public Login() {
        super();
    }
}
//>>> DDD / Domain Event
