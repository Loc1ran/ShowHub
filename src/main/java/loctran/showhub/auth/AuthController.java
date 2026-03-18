package loctran.showhub.auth;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import loctran.showhub.dto.ForgotPasswordRequest;
import loctran.showhub.dto.MessageResponse;
import loctran.showhub.dto.ResetPasswordRequest;
import loctran.showhub.dto.UserRegisterRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<@NonNull AuthResponse> login(
            @Valid @RequestBody AuthRequest authRequest,
            HttpServletResponse response) {
        AuthResponse authResponse = authService.authenticate(authRequest, response);

        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, authResponse.getJwtToken()).body(authResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<@NonNull AuthResponse> registerUser(
            @Valid @RequestBody UserRegisterRequest request) {
        AuthResponse authResponse = authService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.AUTHORIZATION, authResponse.getJwtToken()).body(authResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<@NonNull AuthResponse> refreshToken(
            @CookieValue(value = "refreshToken") String refreshToken
    ) {
        AuthResponse authResponse = authService.refresh(refreshToken);

        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, authResponse.getJwtToken()).body(authResponse);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<@NonNull MessageResponse> forgotPassword(
            @Valid @RequestBody ForgotPasswordRequest request
    ) {
        return ResponseEntity.ok(authService.forgotPassword(request.getEmail()));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<MessageResponse> resetPassword(
            @Valid @RequestBody ResetPasswordRequest request
    ){
        return ResponseEntity.ok(authService.resetPassword(request));
    }
}
