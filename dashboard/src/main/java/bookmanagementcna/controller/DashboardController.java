package bookmanagementcna.controller;

import bookmanagementcna.config.kafka.KafkaProcessor;
import bookmanagementcna.domain.*;
import bookmanagementcna.dto.BookDto;
import bookmanagementcna.dto.BestsellerDto;
import bookmanagementcna.dto.SubscriptionDto;
import bookmanagementcna.infra.BookListRepository;
import bookmanagementcna.infra.BestsellerListRepository;
import bookmanagementcna.infra.SubscriptionListRepository;
import bookmanagementcna.infra.ViewCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final BookListRepository bookListRepository;
    private final BestsellerListRepository bestsellerListRepository;
    private final SubscriptionListRepository subscriptionListRepository;
    private final ViewCountRepository viewCountRepository;
    private final KafkaProcessor kafkaProcessor;

    // 전체 책 목록 조회
    @GetMapping("/books")
    public List<BookDto> getAllBooks() {
        return StreamSupport.stream(bookListRepository.findAll().spliterator(), false)
                .map(book -> new BookDto(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getContentSummary(),
                        book.getCoverUrl(),
                        book.getPublishedDate(),
                        book.getIsAvailable()))
                .collect(Collectors.toList());
    }

    // 개별 책 조회 + 조회수 증가 + 베스트셀러 이벤트 발행
    @GetMapping("/books/{bookId}")
    public BookDto getBookById(@PathVariable Long bookId) {
        BookList book = bookListRepository.findById(bookId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "책을 찾을 수 없습니다: ID = " + bookId));

        // 조회수 증가
        ViewCount viewCount = viewCountRepository.findById(bookId).orElseGet(() -> {
            ViewCount vc = new ViewCount();
            vc.setBookId(bookId);
            vc.setCount(0);
            vc.setTitle(book.getTitle());
            vc.setAuthorName(book.getAuthor());
            vc.setSummaryText(book.getContentSummary());
            vc.setCoverImageUrl(book.getCoverUrl());
            vc.setPublishedDate(book.getPublishedDate());
            return vc;
        });

        viewCount.setCount(viewCount.getCount() + 1);
        viewCountRepository.save(viewCount);

        // 조회수 3일 때 발행
        if (viewCount.getCount() == 3) {
        BestsellerList bestsellerList = new BestsellerList();
        bestsellerList.setId(book.getId());
        bestsellerList.setTitle(book.getTitle());
        bestsellerList.setAuthor(book.getAuthor());
        bestsellerList.setContentSummary(book.getContentSummary());
        bestsellerList.setCoverUrl(book.getCoverUrl());
        bestsellerList.setPublishedDate(book.getPublishedDate());

        bestsellerListRepository.save(bestsellerList);
        }
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getContentSummary(),
                book.getCoverUrl(),
                book.getPublishedDate(),
                book.getIsAvailable());
    }

    @GetMapping("/bestsellers")
    public List<BestsellerDto> getBestsellers() {
        return StreamSupport.stream(bestsellerListRepository.findAll().spliterator(), false)
                .map(b -> new BestsellerDto(
                        b.getId(),
                        b.getTitle(),
                        b.getAuthor(),
                        b.getContentSummary(),
                        b.getCoverUrl(),
                        b.getPublishedDate()))
                .collect(Collectors.toList());
    }

        @GetMapping("/subscribers/{id}")
        public SubscriptionDto getSubscriberById(@PathVariable Long id) {
        SubscriptionList sub = subscriptionListRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "구독자를 찾을 수 없습니다: ID = " + id));

        return new SubscriptionDto(
                sub.getId(),
                sub.getName(),
                sub.getJoinStatus(),
                sub.getPoint());
        }
}
