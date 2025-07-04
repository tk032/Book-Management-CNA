package bookmanagementcna.domain;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "publishManages",
    path = "publishManages"
)

public interface PublishManageRepository
    extends JpaRepository<PublishManage, Long> {}

