package bookmanagementcna.domain;

import javax.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "EBookService_table")
@Data
public class EBookService {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long publicationId;
    private String title;
    private String content;
    private String coverImageUrl;
    private boolean bestSeller;
    private boolean active = true;

    //  전자책 출판 요청 처리
    public void requestPublish() {
        System.out.println(">>> [EBookService] requestPublish 호출됨");
        // 출판 요청 로직 구현
    }

    //  베스트셀러로 표시
    public void markAsBestSeller() {
        this.bestSeller = true;
        System.out.println(">>> [EBookService] markAsBestSeller 호출됨");
    }

    //  전자책 요약 처리
    public void summarizeEbook(String content) {
        System.out.println(">>> [EBookService] summarizeEbook 호출됨: " + content);
        // 요약 생성 로직 (예: AI 요약 API 호출 등)
    }

    //  AI 표지 이미지 생성 처리
    public void updateCoverImage(String imageUrl) {
        this.coverImageUrl = imageUrl;
        System.out.println(">>> [EBookService] updateCoverImage 호출됨: " + imageUrl);
    }

    //  이벤트 핸들러 메서드들
    public void createAiCoverImage(RequestApproved event) {
        System.out.println(">>> [EBookService] createAiCoverImage 호출됨: " + event);
        // event로부터 이미지 URL 생성 후 적용 (예시)
        updateCoverImage("https://ai-generated.com/image/" + event.getBookId());
    }

    public void bookRequest(PointsUsed event) {
        System.out.println(">>> [EBookService] bookRequest 호출됨: " + event);
        // 포인트로 도서 요청 처리
    }

    public void viewIncrease(PointsUsed event) {
        System.out.println(">>> [EBookService] viewIncrease 호출됨: " + event);
        // 조회수 증가 로직
    }

    public void deactivateContent(ReportResolved event) {
        this.active = false;
        System.out.println(">>> [EBookService] deactivateContent 호출됨: " + event);
    }
}
