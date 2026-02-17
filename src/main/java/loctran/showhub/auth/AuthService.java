package loctran.showhub.auth;

import jakarta.servlet.http.HttpServletResponse;
import loctran.showhub.dto.UserDTO;
import loctran.showhub.dto.UserRegisterRequest;
import loctran.showhub.exceptions.BadRequestException;
import loctran.showhub.jwts.Jwt;
import loctran.showhub.jwts.JwtService;
import loctran.showhub.mappers.UserMapper;
import loctran.showhub.user.Role;
import loctran.showhub.user.User;
import loctran.showhub.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse authenticate(AuthRequest authRequest, HttpServletResponse HttpServletResponse) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );

        User user =  userRepository.findByEmail(authRequest.getEmail()).orElseThrow();
        Map<String, Object> claims = setClaims(user);

        UserDTO userDTO = userMapper.toDTO(user);

        Jwt accessToken = jwtService.generateToken(user.getId(), claims);

        return new AuthResponse(userDTO, accessToken.toString());
    }

    private Map<String, Object> setClaims(User user) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("email", user.getEmail());
        claims.put("name", user.getName());
        claims.put("role", user.getRole());

        return claims;
    }

    public AuthResponse register(UserRegisterRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new BadRequestException("Email already exists");
        }

        if(userRepository.existsByUsername(request.getUsername())){
            throw new BadRequestException("Username already exists");
        }

        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);

        Map<String, Object> claims = setClaims(user);

        UserDTO userDTO = userMapper.toDTO(user);

        Jwt accessToken = jwtService.generateToken(user.getId(), claims);

        return new AuthResponse(userDTO, accessToken.toString());
    }
}
