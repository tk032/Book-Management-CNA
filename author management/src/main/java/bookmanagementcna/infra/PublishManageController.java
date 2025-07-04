package bookmanagementcna.infra;
 
import bookmanagementcna.domain.*;
import java.util.List;
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
@CrossOrigin(origins = "*")
@RequestMapping(value="/publish")
@Transactional
public class PublishManageController {
 
    @Autowired
    PublishManageRepository publishManageRepository;
    @PostMapping("/publishes")
    public PublishManage registerPublish(@RequestBody PublishManage publishManage) {
        publishManage.requestPublication(); // 상태: "REQUESTED"
        return publishManageRepository.save(publishManage); // SaveCompleted & PublishRequestRegistered 이벤트 발행
    }
    @PatchMapping("/publishes/{id}")
    public void approvePublish(@PathVariable Long id) {
        PublishManage publishManage = publishManageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("출판 요청이 존재하지 않습니다."));
 
        publishManage.approve();  // 상태: APPROVED
        publishManageRepository.save(publishManage);
 
        RequestApproved event = new RequestApproved(publishManage);
        event.publishAfterCommit();
    }

    @GetMapping("/{id}")
    public PublishManage getPublish(@PathVariable Long id) {
        return publishManageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 출판 요청이 존재하지 않습니다. ID: " + id));
    }

    @GetMapping
    public List<PublishManage> getAllPublishes() {
        return publishManageRepository.findAll();
    }

}


