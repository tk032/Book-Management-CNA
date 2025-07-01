package bookmanagementcna.api;

import bookmanagementcna.application.AI표지이미지생성Handler;
import bookmanagementcna.domain.AI표지이미지생성Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/covers")
public class AI표지이미지Controller {

    @Autowired
    private AI표지이미지생성Handler ai표지이미지생성Handler;

    @PostMapping("/generate")
    public ResponseEntity<?> generateCover(@RequestBody AI표지이미지Request request) {
        AI표지이미지생성Command command = new AI표지이미지생성Command(
            request.getBookId(),
            request.getTitle(),
            request.getDescription()
        );
        ai표지이미지생성Handler.handle(command);
        return ResponseEntity.ok("AI 표지 이미지 생성 요청이 처리되었습니다.");
    }

    public static class AI표지이미지Request {
        private Long bookId;
        private String title;
        private String description;

        public AI표지이미지Request() {}

        public Long getBookId() { return bookId; }
        public void setBookId(Long bookId) { this.bookId = bookId; }

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }
}
