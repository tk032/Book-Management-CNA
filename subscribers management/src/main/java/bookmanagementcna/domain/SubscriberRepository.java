package bookmanagementcna.domain;

import bookmanagementcna.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.Optional; // [추가]

@RepositoryRestResource(
    collectionResourceRel = "subscribers",
    path = "subscribers"
)
public interface SubscriberRepository
    extends PagingAndSortingRepository<Subscriber, Long> {

    // [여기 추가!] 이메일로 회원 찾기
    Optional<Subscriber> findByEmail(String email);

}
