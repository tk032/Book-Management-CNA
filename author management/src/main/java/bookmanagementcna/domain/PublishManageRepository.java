package bookmanagementcna.domain;

import bookmanagementcna.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "publishManages",
    path = "publishManages"
)
public interface PublishManageRepository extends JpaRepository<PublishManage, Long> {}
