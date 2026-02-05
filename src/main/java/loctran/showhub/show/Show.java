package loctran.showhub.show;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "shows")
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Column(name = "poster_url")
    private String posterUrl;

    @Column(name = "banner_url")
    private String bannerUrl;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "avg_rating")
    private BigDecimal avgRating;

    @Column(name = "rating_count")
    private Long ratingCount;

    private String country;

    @Column(name = "created_at", insertable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    private OffsetDateTime updatedAt;

    @OneToMany(mappedBy = "show", orphanRemoval = true)
    private Set<Episode> episodes;

    @ManyToMany
    @JoinTable(
            name = "show_genres",
            joinColumns = @JoinColumn(name = "show_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres;

    @OneToMany(mappedBy = "show")
    private Set<Rating> ratings;
}
