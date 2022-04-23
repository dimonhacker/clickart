package su.clickart.clickart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.clickart.clickart.entity.ShortLink;
import su.clickart.clickart.entity.User;
import su.clickart.clickart.repository.ShortLinkRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShortLinkService {

    @Autowired
    private ShortLinkRepository shortLinkRepository;

    public List<ShortLink> getShortLinks(User user){
        return shortLinkRepository.findByUser(user);
    }

    public void saveShortLink(ShortLink shortLink){
        shortLinkRepository.save(shortLink);
    }

    public ShortLink getById(Long id){
        return shortLinkRepository.findById(id).get();
    }
}
