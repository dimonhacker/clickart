package su.clickart.clickart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.clickart.clickart.entity.OriginalLink;
import su.clickart.clickart.entity.ShortLink;
import su.clickart.clickart.entity.User;
import su.clickart.clickart.repository.OriginalLinkRepository;
import su.clickart.clickart.repository.ShortLinkRepository;

import java.util.List;

@Service
public class LinkService {

    @Autowired
    private ShortLinkRepository shortLinkRepository;

    @Autowired
    private OriginalLinkRepository originalLinkRepository;

    @Autowired
    LinkGenerator linkGenerator;

    public List<ShortLink> getShortLinks(User user) {
        return shortLinkRepository.findByUser(user);
    }

    public void saveShortLink(ShortLink shortLink) {
        shortLinkRepository.save(shortLink);
    }

    public ShortLink getById(Long id) {
        return shortLinkRepository.findById(id).get();
    }

    public ShortLink findByUrl(String url) {
        return shortLinkRepository.findByUrl(url);
    }


    public String genUrl() {
        String url = linkGenerator.getUrl();
        ShortLink slink = findByUrl(url);
        if (slink == null) return url;
        else return genUrl();
    }


    public void delete(ShortLink shortLink) {
        OriginalLink originalLink = shortLink.getOriginalLink();
        shortLink.setUser(null);
        shortLink.setOriginalLink(null);
        shortLinkRepository.save(shortLink);
        shortLinkRepository.delete(shortLink);
        originalLinkRepository.delete(originalLink);
    }
}
