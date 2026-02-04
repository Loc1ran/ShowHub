package loctran.showhub.user;

import jakarta.persistence.*;
import loctran.showhub.post.CommentLike;
import loctran.showhub.post.Post;
import loctran.showhub.post.PostLike;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String username;

    private String email;

    private String password;

    @Column(name = "profile_pic_url")
    private String profilePicUrl;

    private String bio;

    @Column(name = "is_verified")
    private boolean isVerified;

    @Column(name = "created_at", insertable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    private OffsetDateTime updatedAt;

    @OneToMany(mappedBy = "user")
    private Set<Post> posts;

    @OneToMany(mappedBy = "user")
    private Set<PostLike> postLikes;

    @OneToMany(mappedBy = "user")
    private Set<CommentLike> commentLikes;

}
