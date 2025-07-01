package bookmanagementcna.infra;

import bookmanagementcna.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
// @RequestMapping(value="/services")
@Transactional
public class ServiceController {

    @Autowired
    ServiceRepository serviceRepository;

    public void handle(PublishCommand command) {
        Service service = serviceRepository.findById(command.getId()).orElseThrow();
        service.publishRequest(command);
    } //출간요청, 전자책 요약
}
