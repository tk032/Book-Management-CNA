package bookmanagementcna.domain;

import bookmanagementcna.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class ProductAutomaticallyRegistered extends AbstractEvent {

    private Long id;
    private Long pulicationId;
    private Long productId;
    private String productRegistered;
    private Date registeredAt;

    public ProductAutomaticallyRegistered(EBookService aggregate) {
        super(aggregate);
    }

    public ProductAutomaticallyRegistered() {
        super();
    }
}