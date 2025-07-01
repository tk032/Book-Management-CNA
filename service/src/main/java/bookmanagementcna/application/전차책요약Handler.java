package bookmanagementcna.application;

import bookmanagementcna.domain.전자책요약Command;
import bookmanagementcna.domain.전자책요약됨;
import bookmanagementcna.external.AISummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class 전자책요약Handler {

    @Autowired
    AISummaryService aiSummaryService;

    public void handle(전자책요약Command command) {
        String summary = aiSummaryService.summarize(command.getBookContent());

        전자책요약됨 event = new 전자책요약됨();
        event.setBookId(command.getBookId());
        event.setSummary(summary);
        event.publishAfterCommit();
    }
}
