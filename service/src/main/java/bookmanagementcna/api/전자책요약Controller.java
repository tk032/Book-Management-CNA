package bookmanagementcna.api;

import bookmanagementcna.application.전자책요약Handler;
import bookmanagementcna.domain.전자책요약Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ebooks")
public class 전자책요약Controller {

    @Autowired
    private 전자책요약Handler 전자책요약Handler;

    @PostMapping("/summarize")
    public ResponseEntity<?> summarizeEbook(@RequestBody 전자책요약Request request) {
        전자책요약Command command = new 전자책요약Command(request.getBookId(), request.getBookContent());
        전자책요약Handler.handle(command);
        return ResponseEntity.ok("전자책 요약 요청이 처리되었습니다.");
    }

    public static class 전자책요약Request {
        private Long bookId;
        private String bookContent;

        public 전자책요약Request() {}

        public Long getBookId() { return bookId; }
        public void setBookId(Long bookId) { this.bookId = bookId; }

        public String getBookContent() { return bookContent; }
        public void setBookContent(String bookContent) { this.bookContent = bookContent; }
    }
}
