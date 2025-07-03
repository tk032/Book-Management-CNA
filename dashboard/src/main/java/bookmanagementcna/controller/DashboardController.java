package bookmanagementcna.controller;

import bookmanagementcna.domain.BookList;
import bookmanagementcna.domain.BestsellerList;
import bookmanagementcna.domain.SubscriptionList;
import bookmanagementcna.dto.BookDto;
import bookmanagementcna.dto.BestsellerDto;
import bookmanagementcna.dto.SubscriptionDto;
import bookmanagementcna.infra.BookListRepository;
import bookmanagementcna.infra.BestsellerListRepository;
import bookmanagementcna.infra.SubscriptionListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

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
