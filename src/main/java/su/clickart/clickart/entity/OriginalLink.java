package su.clickart.clickart.entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Table(name = "originallink")
@Entity
public class OriginalLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Field can not be empty")
    @URL(regexp = "^(http|https).*", message = "Make sure the url is valid")
    @Column
    private String url;

    @OneToOne(mappedBy = "originalLink", cascade = CascadeType.ALL)
    private ShortLink shortLink;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ShortLink getShortLink() {
        return shortLink;
    }

    public void setShortLink(ShortLink shortLink) {
        this.shortLink = shortLink;
    }

    @Override
    public String toString() {
        return "OriginalLink{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", shortLink=" + shortLink +
                '}';
    }

    public OriginalLink() {
    }
}
