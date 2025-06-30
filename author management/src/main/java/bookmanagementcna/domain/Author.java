package bookmanagementcna.domain;

import bookmanagementcna.AuthorManagementApplication;
import bookmanagementcna.domain.RegistrationRequested;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Author_table")
@Data
//<<< DDD / Aggregate Root
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String name;

    private String introduce;

    private String major;

    private String portfolio;

    private String registerStatus;

    @PostPersist
    public void onPostPersist() {
        RegistrationRequested registrationRequested = new RegistrationRequested(this);
        registrationRequested.publishAfterCommit();
    }

    public static AuthorRepository repository() {
        AuthorRepository authorRepository = AuthorManagementApplication.applicationContext.getBean(
            AuthorRepository.class
        );
        return authorRepository;
    }

    //<<< Clean Arch / Port Method
    public static void approveAuthor(AuthorApproved authorApproved) {
        repository().findById(Long.valueOf(authorApproved.getTargetId()))
            .ifPresent(author -> {
                author.setRegisterStatus("승인됨"); // 승인 상태로 변경
                repository().save(author); // DB에 저장
            });
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root