<<<<<<< HEAD
package bookmanagementcna.domain; 
=======
package bookmanagementcna.domain;
>>>>>>> 095c5db1600412671eb330388e095fbf60263f0a

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
<<<<<<< HEAD
        RegistrationRequested registrationRequested = new RegistrationRequested(
            this
        );
=======
        RegistrationRequested registrationRequested = new RegistrationRequested(this);
>>>>>>> 095c5db1600412671eb330388e095fbf60263f0a
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
<<<<<<< HEAD
        //implement business logic here:

        /** Example 1:  new item 
        Author author = new Author();
        repository().save(author);

        */

        /** Example 2:  finding and process
        

        repository().findById(authorApproved.get???()).ifPresent(author->{
            
            author // do something
            repository().save(author);


         });
        */

=======
        repository().findById(Long.valueOf(authorApproved.getTargetId()))
            .ifPresent(author -> {
                author.setRegisterStatus("승인됨"); // 승인 상태로 변경
                repository().save(author); // DB에 저장
            });
>>>>>>> 095c5db1600412671eb330388e095fbf60263f0a
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root