// 베스트셀러 식별 API
package bookmanagementcna.api;

import bookmanagementcna.domain.EBookServiceCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/best-seller-identify")
public class BestsellerIdentifiedController {

    @Autowired
    private EBookServiceCommandHandler commandHandler;

    @PostMapping
    public void identify(@RequestParam Long publicationId) {
        commandHandler.handleBestsellerIdentified(publicationId);
    }
}