package su.clickart.clickart.entity;

import javax.persistence.*;

@Table(name = "originallink")
@Entity
public class OriginalLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String url;

    @OneToOne(mappedBy = "originalLink")
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
