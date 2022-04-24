package su.clickart.clickart.service;

import org.springframework.stereotype.Component;

import java.util.Random;


@Component
public class LinkGenerator {
    private String url;
    int startChar = 65;
    int endChar = 90;
    int startSmallChar = 97;
    int endSmallChar = 122;

    Random r = new Random();

    public String getUrl() {
        genUrl();
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private void genUrl() {
        int length = 4;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            boolean isSmall = r.nextBoolean();
            int delta, start;
            if (isSmall) {
                delta = endSmallChar - startSmallChar;
                start = startSmallChar;
            } else {
                delta = endChar - startChar;
                start = startChar;
            }
            char symb = (char) (r.nextInt(delta) + start);
            sb.append(symb);
        }
        setUrl(sb.toString());
    }
}
