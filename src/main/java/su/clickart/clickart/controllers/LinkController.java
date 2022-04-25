package su.clickart.clickart.controllers;

import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import su.clickart.clickart.entity.ShortLink;
import su.clickart.clickart.service.GeneratorQR;
import su.clickart.clickart.service.LinkService;

import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/link")
public class LinkController {

    @Autowired
    LinkService linkService;

    @Autowired
    GeneratorQR generatorQR;

    @GetMapping("/{id}")
    public String index(@PathVariable("id") String id, Model model, HttpServletResponse response) {
        boolean isLink = false;
        try {
            Long val = Long.parseLong(id);
            ShortLink shortLink;
            shortLink = linkService.getById(val);
            if (shortLink != null) {
                String base64Image = generatorQR.createQR(shortLink.getUrl(), Charset.defaultCharset().toString(), 200, 200);
                if (base64Image != null)
                    model.addAttribute("QR", base64Image);
                model.addAttribute("link", shortLink);
                model.addAttribute("originallink", shortLink.getOriginalLink());
                return "link.html";
            }
        } catch (NumberFormatException exception) {
            isLink = true;
        }

        if (isLink) {
            ShortLink shortLink = null;
            shortLink = linkService.findByUrl(id);
            if (shortLink != null) {
                String url = shortLink.getOriginalLink().getUrl();
                return "redirect:" + url;
            }
        }
        response.setStatus(404);
        return "404.html";
    }

    @GetMapping("/delete/{id}")
    public String deleteUrl(@PathVariable("id") String id) {
        try {
            ShortLink shortLink = linkService.findByUrl(id);
            linkService.delete(shortLink);
        } catch (NoSuchElementException exception) {
        }
        return "redirect:/";
    }


}
