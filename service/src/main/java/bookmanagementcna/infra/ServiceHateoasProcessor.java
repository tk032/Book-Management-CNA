package bookmanagementcna.infra;

import bookmanagementcna.domain.EBookService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class ServiceHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<EBookService>> {

    @Override
    public EntityModel<EBookService> process(EntityModel<EBookService> model) {
        return model;
    }
}
