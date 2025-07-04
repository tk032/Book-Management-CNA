package bookmanagementcna.domain;

import bookmanagementcna.domain.EBookService;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "eBookServices", path = "eBookServices")
public interface ServiceRepository extends PagingAndSortingRepository<EBookService, Long> {}
