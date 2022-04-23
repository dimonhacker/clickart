package su.clickart.clickart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import su.clickart.clickart.entity.ShortLink;
import su.clickart.clickart.service.ShortLinkService;

@Controller
@RequestMapping("/link")
public class LinkController {

    @Autowired
    ShortLinkService shortLinkService;

    @GetMapping("/{id}")
    public String index(@PathVariable("id")String id, Model model){
        ShortLink shortLink =shortLinkService.getById(Long.parseLong(id));
        model.addAttribute("link", shortLink);
        model.addAttribute("originallink", shortLink.getOriginalLink());
        return "link.html";
    }
}
