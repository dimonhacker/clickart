package su.clickart.clickart.entity;

import javax.persistence.*;

@Table(name="shortlink")
@Entity
public class ShortLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String url;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn (name="user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "original_id")
    private OriginalLink originalLink;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OriginalLink getOriginalLink() {
        return originalLink;
    }

    public void setOriginalLink(OriginalLink originalLink) {
        this.originalLink = originalLink;
    }

    @Override
    public String toString() {
        return "ShortLink{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", user=" + user +
                ", originalLink=" + originalLink +
                '}';
    }

    public ShortLink() {
    }
}
