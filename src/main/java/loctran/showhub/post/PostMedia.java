package loctran.showhub.post;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@Table(name = "post_media")
public class PostMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "media_type")
    private String mediaType;

    @Column(name = "media_order")
    private Long mediaOrder;

    @Column(name = "original_url")
    private String originalUrl;

    @Column(name = "compressed_url")
    private String compressedUrl;

    @Column(name = "hls_url")
    private String hls_url;

    private Long duration;

    @Column(name = "created_at", insertable = false, updatable = false)
    private OffsetDateTime createdAt;
}
