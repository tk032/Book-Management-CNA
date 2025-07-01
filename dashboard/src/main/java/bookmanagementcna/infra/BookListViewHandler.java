package bookmanagementcna.infra;

import bookmanagementcna.config.kafka.KafkaProcessor;
import bookmanagementcna.domain.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class BookListViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private BookListRepository bookListRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenRequestApproved_then_CREATE_1(
        @Payload RequestApproved requestApproved
    ) {
        try {
            if (!requestApproved.validate()) return;

            // view 객체 생성
            BookList bookList = new BookList();
            // view 객체에 이벤트의 Value 를 set 함
            bookList.setId(requestApproved.getId());
            bookList.setTitle(requestApproved.getBookTitle());
            bookList.setAuthor(requestApproved.getAuthorName());
            bookList.setIsAvailable(false);
            // view 레파지 토리에 save
            bookListRepository.save(bookList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenBookPublicated_then_UPDATE_1(
        @Payload BookPublicated bookPublicated
    ) {
        try {
            if (!bookPublicated.validate()) return;
            // view 객체 조회
            Optional<BookList> bookListOptional = bookListRepository.findById(
                bookPublicated.getId()
            );

            if (bookListOptional.isPresent()) {
                BookList bookList = bookListOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                bookList.setCoverUrl(bookPublicated.getCoverImageUrl());
                bookList.setIsAvailable(true);
                bookList.setContentSummary(bookPublicated.getSummaryText());
                bookList.setPublishedDate(bookPublicated.getPublishedDate());
                bookList.setCoverUrl(bookPublicated.getCoverImageUrl());
                // view 레파지 토리에 save
                bookListRepository.save(bookList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @StreamListener(KafkaProcessor.INPUT)
    public void whenReportResolved_then_DELETE_1(
        @Payload ReportResolved reportResolved
    ) {
        try {
            if (!reportResolved.validate()) return;
            // view 레파지 토리에 삭제 쿼리
            bookListRepository.deleteById(reportResolved.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //>>> DDD / CQRS
}
