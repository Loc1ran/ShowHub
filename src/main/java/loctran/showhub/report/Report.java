package loctran.showhub.report;

import jakarta.persistence.*;
import loctran.showhub.user.User;
import lombok.*;

import java.time.OffsetDateTime;

@Builder
@Entity
@Getter
@Setter
@Table(name = "reports")
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reporter_id")
    private User reporter;

    @Enumerated(EnumType.STRING)
    @Column(name = "reported_entity_type")
    private EntityType reportedEntityType;

    @Column(name = "reported_entity_id")
    private Long reportedEntityId;

    @ManyToOne
    @JoinColumn(name = "reported_user_id")
    private User reported;

    @Enumerated(EnumType.STRING)
    private ReportReason reason;

    private String description;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ReportStatus status = ReportStatus.PENDING;

    @ManyToOne
    @JoinColumn(name = "moderator_id")
    private User moderator;

    @Column(name = "moderator_notes")
    private String moderatorNotes;

    @Column(name = "created_at", insertable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "resolved_at", insertable = false)
    private OffsetDateTime resolved_at;

    @PrePersist
    protected void onCreate() {
        createdAt = OffsetDateTime.now();
    }

    public void resolve(User moderator, ReportStatus status, String moderatorNotes) {
        if(status != ReportStatus.SUCCESS && status != ReportStatus.FAILED) {
            throw new IllegalArgumentException("Case is ongoing");
        }

        this.status = status;
        this.moderator = moderator;
        this.moderatorNotes = moderatorNotes;
        this.resolved_at = OffsetDateTime.now();
    }
}
