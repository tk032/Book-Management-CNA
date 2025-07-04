package bookmanagementcna.infra;

import bookmanagementcna.domain.ViewCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewCountRepository extends JpaRepository<ViewCount, Long> {
}
