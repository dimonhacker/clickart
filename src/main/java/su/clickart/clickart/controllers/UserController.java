package su.clickart.clickart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import su.clickart.clickart.entity.OriginalLink;
import su.clickart.clickart.entity.ShortLink;
import su.clickart.clickart.entity.User;
import su.clickart.clickart.service.LinkService;
import su.clickart.clickart.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;


@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    LinkService linkService;


    @GetMapping("/")
    public String getIndex(@CookieValue(value = "user", required = false) String userCookie, Model model, HttpServletResponse response) {
        User user = null;
        model.addAttribute("originalLink", new OriginalLink());
        boolean cookieIsOk=false;
        if(userCookie!=null){
            List<User> users;
            users = userService.getUsersByName(userCookie);
            if (users!=null && users.size()>0) {user = users.get(0); cookieIsOk=true;}
        }
        if (!cookieIsOk) {
            user = new User();
            Cookie cookie = userService.createCookie();
            response.addCookie(cookie);
            user.setName(String.valueOf(cookie.getValue()));
            userService.saveUser(user);
        }
        List<ShortLink> links = linkService.getShortLinks(user);
        model.addAttribute("links", links);
        return "index";
    }


    @PostMapping("/")
    public String createShortLink(@Valid @ModelAttribute("originalLink") OriginalLink originalLink, BindingResult bindingResult, @CookieValue(value = "user", required = true) String userCookie, Model model) {

        if (bindingResult.hasErrors()) {
            return "index";
        }
        User user = null;
        List<User> users = userService.getUsersByName(userCookie);
        if (users != null) user = users.get(0);
        ShortLink shortLink = new ShortLink();
        shortLink.setUrl(linkService.genUrl());
        shortLink.setUser(user);
        shortLink.setOriginalLink(originalLink);
        linkService.saveShortLink(shortLink);
        /*if(userCookie==null){
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
        }*/
        return "redirect:/?shortLink=" + shortLink.getUrl();
    }
}
