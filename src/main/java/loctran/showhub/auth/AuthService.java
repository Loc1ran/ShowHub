package loctran.showhub.auth;

import jakarta.servlet.http.HttpServletResponse;
import loctran.showhub.jwts.Jwt;
import loctran.showhub.jwts.JwtService;
import loctran.showhub.user.User;
import loctran.showhub.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public AuthResponse authenticate(AuthRequest authRequest, HttpServletResponse httpServletResponse) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );

        User user =  userRepository.findByEmail(authRequest.getEmail()).orElseThrow();
        Map<String, Object> claims = setClaims(user);

        Jwt accessToken = jwtService.generateToken(user.getId(), claims);

        return new AuthResponse(accessToken.toString());
    }

    private Map<String, Object> setClaims(User user) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("email", user.getEmail());
        claims.put("name", user.getName());
        claims.put("role", user.getRole());

        return claims;
    }
}
