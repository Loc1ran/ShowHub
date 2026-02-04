package loctran.showhub.show;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "episodes")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;

    private int season;

    @Column(name = "episode_number")
    private int episodeNumber;

    private String title;

    @Column(name = "air_date")
    private LocalDate airDate;

    private String description;
}
