package bookmanagementcna.domain;

import bookmanagementcna.infra.AbstractEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 콘텐츠 열람 허용 이벤트
 * 구독자가 포인트를 사용하여 콘텐츠 열람 권한을 얻었을 때 발생
 */

//<<< DDD / Domain Event
@Getter
@Setter
@ToString
public class ContentViewEnabled extends AbstractEvent {

    private final Long id;
    private final String email;
    private final String name;
    private Boolean joinStatus;
    private String address;
    private Boolean ktCustomer;
    private String password; 
    
    public ContentViewEnabled(Subscriber aggregate) {
        super(aggregate);
        this.id = aggregate.getId();
        this.email = aggregate.getEmail();
        this.name = aggregate.getName();
        this.joinStatus = aggregate.getJoinStatus();
    }

    public ContentViewEnabled() {
        super();
        this.id = null;
        this.email = null;
        this.name = null;
        this.joinStatus = null;
    }
}

//>>> DDD / Domain Event
