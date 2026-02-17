package loctran.showhub.auth;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import loctran.showhub.dto.UserRegisterRequest;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
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
            @Valid @RequestBody UserRegisterRequest request){
        AuthResponse authResponse = authService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.AUTHORIZATION, authResponse.getJwtToken()).body(authResponse);
    }
}
