package loctran.showhub.verification;

import jakarta.persistence.*;
import loctran.showhub.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
    private LocalDateTime expiresAt;

    private boolean used = false;

    public PasswordResetToken(User user, String token, int expiryMinutes) {
        this.user = user;
        this.token = token;
        this.expiresAt = LocalDateTime.now().plusMinutes(expiryMinutes);
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expiresAt);
    }
}
