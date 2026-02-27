package loctran.showhub.post;

import jakarta.persistence.*;
import loctran.showhub.hashtag.HashTag;
import loctran.showhub.show.Show;
import loctran.showhub.user.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

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
    private Long likesCount;

    @Column(name = "comments_count")
    private Long commentsCount;

    @Column(name = "shares_count")
    private Long sharesCount;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "post")
    private Set<PostMedia> postMedia;

    @OneToMany(mappedBy = "post")
    private Set<PostLike> postLikes;

    @ManyToMany
    @JoinTable(
            name = "post_hashtags",
            joinColumns = @JoinColumn( name ="post_id"),
            inverseJoinColumns = @JoinColumn(name = "hashtag_id")
    )
    private Set<HashTag> hashTags;
}
