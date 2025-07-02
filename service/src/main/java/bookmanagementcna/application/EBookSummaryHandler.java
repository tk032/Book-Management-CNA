package bookmanagementcna.application;

import bookmanagementcna.domain.EBookSummaryCommand;
import bookmanagementcna.domain.EBookSummary;
import bookmanagementcna.external.AISummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EBookSummaryHandler {

    @Autowired
    private AISummaryService aiSummaryService;

    public void handle(EBookSummaryCommand command) {
        // GPT API를 통해 요약
        String summary = aiSummaryService.summarize(command.getBookContent());

        EBookSummary event = new EBookSummary();
        event.setBookId(command.getBookId());
        event.setSummary(summary);
        event.publishAfterCommit();
    }
}
