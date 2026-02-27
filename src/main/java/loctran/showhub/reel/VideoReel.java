package loctran.showhub.reel;

import jakarta.persistence.*;
import loctran.showhub.show.Show;
import loctran.showhub.user.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "video_reels")
public class VideoReel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;

    private String tittle;

    private String description;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    private Integer duration;

    @Column(name = "view_count")
    private Long viewsCount;

    @Column(name = "likes_count")
    private Integer likesCount;

    @Column(name = "comments_count")
    private Integer commentsCount;

    @Column(name = "shares_count")
    private Integer sharesCount;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;
}
