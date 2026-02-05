package loctran.showhub.hashtag;

import jakarta.persistence.*;
import loctran.showhub.post.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "hashtags")
public class HashTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "usage_count")
    private Integer usageCount;

    @Column(name = "created_at", insertable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "last_used_at", insertable = false)
    private OffsetDateTime lastUsedAt;

    @ManyToMany(mappedBy = "hashTags")
    private Set<Post> posts;

    @PreUpdate
    protected void onUpdate() {
        this.lastUsedAt = OffsetDateTime.now();
    }
}
