package loctran.showhub.user;

import jakarta.persistence.*;
import loctran.showhub.notification.Notification;
import loctran.showhub.post.CommentLike;
import loctran.showhub.post.Post;
import loctran.showhub.post.PostLike;
import loctran.showhub.show.Rating;
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

    @Enumerated(EnumType.STRING)
    private Role role;

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

    @OneToMany(mappedBy = "user")
    private Set<Rating>  ratings;

    @OneToMany(mappedBy = "follower")
    private Set<Follow> follower;

    @OneToMany(mappedBy = "following")
    private Set<Follow> following;

    @OneToMany(mappedBy = "user")
    private Set<Notification> notifications;

    @OneToMany(mappedBy = "actor")
    private Set<Notification> triggerNotifications;

    @OneToOne(mappedBy = "user")
    private UserSetting setting;

    @OneToMany(mappedBy = "sender")
    private Set<Message> sender;

    @OneToMany(mappedBy = "receiver")
    private Set<Message> receiver;

    private boolean emailVerified = false;

    private boolean enabled = true;

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }
}
