//  전자책 요약 API
package bookmanagementcna.api;

import bookmanagementcna.application.EBookSummaryHandler;
import bookmanagementcna.domain.EBookSummaryCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ebook-summary")
public class EBookSummaryController {

    @Autowired
    private EBookSummaryHandler handler;

    @PostMapping
    public void summarize(@RequestBody EBookSummaryCommand command) {
        handler.handle(command);
    }
}
