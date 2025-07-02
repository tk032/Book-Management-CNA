package bookmanagementcna.domain;

import bookmanagementcna.SubscribersManagementApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Subscriber_table")
@Data
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    private String address;
    private String message;
    private Integer point;
    private Boolean joinStatus;
    private Boolean ktCustomer;
    private String loginStatus;
    private String region;       // 지역 정보
    private String password;     // 비밀번호

    @Transient
    private final List<Object> domainEvents = new ArrayList<>();

    public void registerEvent(Object event) {
        this.domainEvents.add(event);
    }

    public List<Object> getDomainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    public void clearEvents() {
        domainEvents.clear();
    }

    @PrePersist
    public void onPrePersist() {
        if (this.point == null) {
            this.point = 1000;
        }
    }

    @PostPersist
    public void onPostPersist() {
        SignedIn signedIn = new SignedIn(this);
        registerEvent(signedIn);
        PointsAdded pointsAdded = new PointsAdded(this);
        registerEvent(pointsAdded);
    }

    public void login() {
        this.loginStatus = "LOGIN";
        Login login = new Login(this);
        registerEvent(login);
    }

    public void logout() {
        this.loginStatus = "LOGOUT";
        Logout logout = new Logout(this);
        registerEvent(logout);
    }

    public void usePointForContent(int amount) throws InsufficientPointsException {
        if (Boolean.TRUE.equals(this.unlimitedPlan)) {
            ContentViewEnabled contentViewEnabled = new ContentViewEnabled(this);
            registerEvent(contentViewEnabled);
            return;
        }
        if (this.point == null || this.point < amount) {
            throw new InsufficientPointsException(
                "포인트가 " + (amount - (this.point != null ? this.point : 0)) + "점 부족해요!"
            );
        }
        this.point -= amount;
        PointsUsed pointsUsed = new PointsUsed(this);
        registerEvent(pointsUsed);
        ContentViewEnabled contentViewEnabled = new ContentViewEnabled(this);
        registerEvent(contentViewEnabled);
    }

    public void addPoints(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("0 이상의 포인트만 충전 가능해요!");
        }
        this.point = (this.point == null) ? amount : this.point + amount;
        PointsAdded pointsAdded = new PointsAdded(this);
        registerEvent(pointsAdded);
    }

    public void subscribeUnlimitedPlan() {
        this.unlimitedPlan = true;
    }

    public void unsubscribeUnlimitedPlan() {
        this.unlimitedPlan = false;
    }

    public static class InsufficientPointsException extends Exception {
        public InsufficientPointsException(String message) {
            super(message);
        }
    }

    public static SubscriberRepository repository() {
        SubscriberRepository subscriberRepository = SubscribersManagementApplication.applicationContext.getBean(
            SubscriberRepository.class
        );
        return subscriberRepository;
    }

    public static void updateFeedWhenBookInfoTransferred(BookPublicated bookPublicated) {
        // 구현 로직 생략
    }

    public static void bookInfoSave(BookInfoSended bookInfoSended) {
        // 구현 로직 생략
    }


    public void updateBookFeed(Long bookId, String title) {
        // 실제 피드 업데이트 로직 구현
        this.message = "New book: " + title; // 예시
    }

    public void addRecommendedBook(Long bookId) {
        // 추천 도서 추가 로직 구현
        this.message = "Recommended: " + bookId; // 예시
    }

}
