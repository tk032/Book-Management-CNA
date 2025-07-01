package bookmanagementcna.domain;

import bookmanagementcna.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "authors", path = "authors")
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByEmail(String email);
}
