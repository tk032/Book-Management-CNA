//  출간 요청 API
package bookmanagementcna.api;

import bookmanagementcna.domain.EBookServiceCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publish-request")
public class RequestApprovedController {

    @Autowired
    private EBookServiceCommandHandler commandHandler;

    @PostMapping
    public void publish(@RequestParam Long publicationId) {
        commandHandler.handleRequestApproved(publicationId);
    }
}