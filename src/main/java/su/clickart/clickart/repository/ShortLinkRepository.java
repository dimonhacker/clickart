package su.clickart.clickart.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import su.clickart.clickart.entity.ShortLink;
import su.clickart.clickart.entity.User;

import java.util.List;

@Repository
public interface ShortLinkRepository extends CrudRepository<ShortLink,Long> {
    public List<ShortLink> findByUser(User user);
}
