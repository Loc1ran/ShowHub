package loctran.showhub.dto;


import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
public class ResetPasswordRequest {
    @NotBlank
    private String token;

    @NotBlank(message = "password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String newPassword;

    @NotBlank
    private String confirmNewPassword;

    @AssertTrue(message = "Passwords do not match")
    public boolean isPasswordsMatch(){
        return newPassword != null && newPassword.equals(confirmNewPassword);
    }
}

