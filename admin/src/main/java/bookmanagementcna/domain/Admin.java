package bookmanagementcna.domain;

import bookmanagementcna.AdminApplication;
import bookmanagementcna.domain.AuthorApproved;
import bookmanagementcna.domain.BookApproved;
import bookmanagementcna.domain.ReportResolved;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Admin_table")
@Data
//<<< DDD / Aggregate Root
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long requestId;

    private String requestType; // AUTHOR, BOOK, REPORT

    private String targetId;

    private String requestedAt;

<<<<<<< HEAD
    private String status;      // APPROVED, REJECTED
=======
    private String status;      // APPROVED, REJECTED, RESOLVED
>>>>>>> 095c5db1600412671eb330388e095fbf60263f0a

    private Long adminId;

    private Date approvedAt;

    private String message;

    @PostPersist
    public void onPostPersist() {
<<<<<<< HEAD
        ReportResolved reportResolved = new ReportResolved(this);
        reportResolved.publishAfterCommit();

        BookApproved bookApproved = new BookApproved(this);
        bookApproved.publishAfterCommit();
=======
        if ("REPORT".equals(this.requestType)) {
            ReportResolved reportResolved = new ReportResolved(this);
            reportResolved.publishAfterCommit();
        }
        else if ("BOOK".equals(this.requestType)) {
            BookApproved bookApproved = new BookApproved(this);
            bookApproved.publishAfterCommit();
        }
>>>>>>> 095c5db1600412671eb330388e095fbf60263f0a
    }

    @PostUpdate
    public void onPostUpdate() {
<<<<<<< HEAD
        AuthorApproved authorApproved = new AuthorApproved(this);
        authorApproved.publishAfterCommit();
=======
        if ("AUTHOR".equals(this.requestType)) {
            AuthorApproved authorApproved = new AuthorApproved(this);
            authorApproved.publishAfterCommit();
        }
>>>>>>> 095c5db1600412671eb330388e095fbf60263f0a
    }

    public static AdminRepository repository() {
        AdminRepository adminRepository = AdminApplication.applicationContext.getBean(
            AdminRepository.class
        );
        return adminRepository;
    }

    //<<< Clean Arch / Port Method
<<<<<<< HEAD
    public static void requestRegister(
        RegistrationRequested registrationRequested
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        Admin admin = new Admin();
        repository().save(admin);

        */

        /** Example 2:  finding and process
        

        repository().findById(registrationRequested.get???()).ifPresent(admin->{
            
            admin // do something
            repository().save(admin);


         });
        */

=======
    public static void requestRegister(RegistrationRequested event) {
        Admin admin = new Admin();
        admin.setRequestId(event.getId());
        admin.setRequestType("AUTHOR");
        admin.setTargetId(event.getEmail());
        admin.setStatus("PENDING");
        admin.setRequestedAt(new Date().toString());
        repository().save(admin);
>>>>>>> 095c5db1600412671eb330388e095fbf60263f0a
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
<<<<<<< HEAD
    public static void requestRegister(
        PublishRequestRegistered publishRequestRegistered
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        Admin admin = new Admin();
        repository().save(admin);

        */

        /** Example 2:  finding and process
        

        repository().findById(publishRequestRegistered.get???()).ifPresent(admin->{
            
            admin // do something
            repository().save(admin);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void approveLogin(Login login) {
        //implement business logic here:

        /** Example 1:  new item 
        Admin admin = new Admin();
        repository().save(admin);

        */

        /** Example 2:  finding and process
        

        repository().findById(login.get???()).ifPresent(admin->{
            
            admin // do something
            repository().save(admin);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void approveLogout(Logout logout) {
        //implement business logic here:

        /** Example 1:  new item 
        Admin admin = new Admin();
        repository().save(admin);

        */

        /** Example 2:  finding and process
        

        repository().findById(logout.get???()).ifPresent(admin->{
            
            admin // do something
            repository().save(admin);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
=======
    public static void requestRegister(PublishRequestRegistered event) {
       Admin admin = new Admin();
        admin.setRequestId(event.getId());
        admin.setRequestType("BOOK");
        admin.setTargetId(event.getAuthorId());
        admin.setStatus("PENDING");
        admin.setRequestedAt(new Date().toString());
        repository().save(admin);
    }

    public static void requestResolve(ContentReported event) {
        Admin admin = new Admin();
        admin.setRequestId(event.getId());
        admin.setRequestType("REPORT");
        admin.setTargetId(event.getReportedContentId());
        admin.setStatus("PENDING");
        admin.setRequestedAt(new Date().toString());
        repository().save(admin);
    }

    public static void approveLogin(Login login) {
        System.out.println("Login approved for: " + login.getUserId());
    }

   public static void approveLogout(Logout logout) {
        System.out.println("Logout approved for: " + logout.getUserId());
    }

}
>>>>>>> 095c5db1600412671eb330388e095fbf60263f0a
