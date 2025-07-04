package bookmanagementcna.infra;

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
    public void requestSummary(@PathVariable Long subscriberId, @RequestBody BookSummaryRequested request) {
        request.setSubscriberId(subscriberId);
        bookSummaryService.requestBookSummary(request);
    }
}
