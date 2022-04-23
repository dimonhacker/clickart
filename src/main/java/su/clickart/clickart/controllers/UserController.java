package su.clickart.clickart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import su.clickart.clickart.entity.OriginalLink;
import su.clickart.clickart.entity.ShortLink;
import su.clickart.clickart.entity.User;
import su.clickart.clickart.repository.UserRepository;
import su.clickart.clickart.service.ShortLinkService;
import su.clickart.clickart.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ShortLinkService shortLinkService;

    @GetMapping("/")
    public String getIndex(@RequestParam (value = "shortLink", required = false) String shortLink, @CookieValue(value = "user", required = false) String userCookie, Model model, HttpServletResponse response){
        User user = null;
        if(userCookie==null){
            user = new User();
            Cookie cookie = userService.createCookie();
            response.addCookie(cookie);
            user.setName(String.valueOf(cookie.getValue()));
            userService.saveUser(user);
        }
        else {
            List<User> users = userService.getUsersByName(userCookie);
            if (users.size() > 0) user = users.get(0);
        }
        List<ShortLink> links = shortLinkService.getShortLinks(user);
        model.addAttribute("links",links);
        return "index.html";
    }

    @PostMapping("/create")
    public String createShortLink(@RequestParam(value = "url", required = false) String url, @CookieValue(value = "user", required = false) String userCookie, Model model, HttpServletResponse response){
        User user = null;
        if(userCookie==null){
            user = new User();
            Cookie cookie = userService.createCookie();
            user.setName(String.valueOf(cookie.getValue()));
            response.addCookie(cookie);
            userService.saveUser(user);
        }
        else {
            List<User> users = userService.getUsersByName(userCookie);
            if (users.size() > 0) user = users.get(0);
        }
        if(url!=null) {
            ShortLink shortLink = new ShortLink();
            OriginalLink originalLink = new OriginalLink();
            originalLink.setUrl(url);
            shortLink.setUrl("abracadabra");
            shortLink.setUser(user);
            shortLink.setOriginalLink(originalLink);
            shortLinkService.saveShortLink(shortLink);
            model.addAttribute("url", url);
            System.out.println(url);
        }
        return "redirect:/?shortLink="+url;
    }
}
