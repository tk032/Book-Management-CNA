package bookmanagementcna.infra;

import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bookmanagementcna.domain.BookSummaryRequested;
import bookmanagementcna.service.BookSummaryService;  // import 추가
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/subscribers")
@RequiredArgsConstructor  // lombok 생성자 자동 생성
public class BookSummaryController {

    private final BookSummaryService bookSummaryService;  // 필드 선언


@PostMapping("/{subscriberId}/book-summary")
public String requestSummary(@PathVariable Long subscriberId, @RequestBody Map<String, Object> body) {
    String bookTitle = (String) body.get("bookTitle");
    String bookContent = (String) body.get("bookContent");

    BookSummaryRequested request = new BookSummaryRequested();
    request.setSubscriberId(subscriberId);
    request.setBookTitle(bookTitle);
    request.setBookContent(bookContent);

    // 요약 결과를 리턴
    return bookSummaryService.requestBookSummary(request);
  }
}
