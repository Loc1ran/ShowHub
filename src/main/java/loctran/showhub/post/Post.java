package loctran.showhub.post;

import jakarta.persistence.*;
import loctran.showhub.show.Show;
import loctran.showhub.user.User;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String content;

    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;

    @Column(name = "likes_count")
    private int likesCount;

    @Column(name = "comments_count")
    private int commentsCount;

    @Column(name = "shares_count")
    private int sharesCount;

    @Column(name = "created_at", insertable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    private OffsetDateTime updatedAt;
}
