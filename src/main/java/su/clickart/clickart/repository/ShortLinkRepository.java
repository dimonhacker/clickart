package su.clickart.clickart.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import su.clickart.clickart.entity.ShortLink;
import su.clickart.clickart.entity.User;

import java.util.List;

@Repository
public interface ShortLinkRepository extends CrudRepository<ShortLink, Long> {
     List<ShortLink> findByUser(User user);

     @Query(value = "SELECT * FROM shortlink as s WHERE s.url  COLLATE utf8_bin = :url", nativeQuery = true)
     ShortLink findByUrl(String url);

}
