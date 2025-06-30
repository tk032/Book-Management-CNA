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

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/services")
@Transactional
public class ServiceController {

    @Autowired
    ServiceRepository serviceRepository;
<<<<<<< HEAD
=======

    public void handle(PublishCommand command) {
        Service service = serviceRepository.findById(command.getId()).orElseThrow();
        service.publishRequest(command);
    } //출간요청, 전자책 요약
>>>>>>> 095c5db1600412671eb330388e095fbf60263f0a
}
//>>> Clean Arch / Inbound Adaptor
