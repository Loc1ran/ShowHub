package loctran.showhub.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "password_reset_tokens")
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "expires_at")
    private OffsetDateTime expiresAt;

    private boolean used = false;

    public PasswordResetToken(User user, String token, int expiryMinutes) {
        this.user = user;
        this.token = token;
        this.expiresAt = OffsetDateTime.now().plusMinutes(expiryMinutes);
    }

    public boolean isExpired() {
        return OffsetDateTime.now().isAfter(this.expiresAt);
    }
}
