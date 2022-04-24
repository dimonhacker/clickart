package su.clickart.clickart.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import su.clickart.clickart.entity.OriginalLink;

@Repository
public interface OriginalLinkRepository extends CrudRepository<OriginalLink, Long> {
}
