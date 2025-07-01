package bookmanagementcna.infra;

import bookmanagementcna.domain.*;

import java.time.LocalDateTime;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/authors")
@Transactional
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    @PatchMapping("/{id}/approve")
    public void approveAuthor(@PathVariable Long id) {

        // 작가 조회
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("작가를 찾을 수 없습니다. id=" + id));

        // 상태 변경 및 저장
        author.setRegisterStatus("승인됨");  // 또는 enum 사용 시 RegisterStatus.APPROVED
        authorRepository.save(author);

        // 이벤트 생성 및 발행
        AuthorApproved approvedEvent = new AuthorApproved(author);
        approvedEvent.setAdminId("admin01");  // 관리자 ID 테스트용
        approvedEvent.setApprovedAt(LocalDateTime.now().toString());
        approvedEvent.setMessage("승인 처리 완료");

        approvedEvent.publishAfterCommit();
    }


    @PostMapping("/authors")
    public Author registerAuthor(@RequestBody Author author) {
        author.setRegisterStatus("요청됨"); // 초기 상태 설정
        return authorRepository.save(author); // 저장 + @PostPersist에서 이벤트 발행됨
    }


    @GetMapping("/authors/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("작가를 찾을 수 없습니다. id=" + id));
    }

}
//>>> Clean Arch / Inbound Adaptor
