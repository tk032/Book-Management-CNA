// AI 표지 이미지 생성 API
package bookmanagementcna.api;

import bookmanagementcna.domain.EBookServiceCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai-cover-image")
public class AICoverImageController {

    @Autowired
    private EBookServiceCommandHandler commandHandler;

    @PostMapping
    public void createImage(@RequestParam Long publicationId, @RequestParam String imageUrl) {
        commandHandler.handleAiCoverImage(publicationId, imageUrl);
    }
}
