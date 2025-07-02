package bookmanagementcna.infra;

import bookmanagementcna.domain.EBookService;
import bookmanagementcna.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class ServiceHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Service>> {

    @Override
    public EntityModel<Service> process(EntityModel<Service> model) {
        return model;
    }
}
