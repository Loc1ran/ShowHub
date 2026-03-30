package loctran.showhub.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import loctran.showhub.dto.*;
import loctran.showhub.exceptions.BadRequestException;
import loctran.showhub.exceptions.InvalidTokenException;
import loctran.showhub.jwts.Jwt;
import loctran.showhub.jwts.JwtService;
import loctran.showhub.mappers.UserMapper;
import loctran.showhub.user.Role;
import loctran.showhub.user.User;
import loctran.showhub.user.UserRepository;
import loctran.showhub.user.UserService;
import loctran.showhub.verification.EmailService;
import loctran.showhub.verification.EmailVerificationTokenRepository;
import loctran.showhub.verification.PasswordResetToken;
import loctran.showhub.verification.PasswordResetTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final EmailVerificationTokenRepository emailVerificationTokenRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final UserService userService;

    @Value("${app.email.verification-expiry-hours:24}")
    private int verificationExpiryHours;

    @Value("${app.email.reset-expiry-minutes:15}")
    private int resetExpiryMinutes;

    public AuthResponse authenticate(AuthRequest authRequest, HttpServletResponse response) {
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
        Jwt refreshToken =  jwtService.generateRefreshToken(user.getId(), claims);

        Cookie cookie = new Cookie("refreshToken", refreshToken.toString());
        cookie.setHttpOnly(true);
        cookie.setPath("/api/v1/auth/refresh");
        cookie.setMaxAge(604800);
        cookie.setSecure(false);
        response.addCookie(cookie);

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

        User user = userService.registerUser(request);

        Map<String, Object> claims = setClaims(user);

        UserDTO userDTO = userMapper.toDTO(user);

        Jwt accessToken = jwtService.generateToken(user.getId(), claims);

        return new AuthResponse(userDTO, accessToken.toString());
    }

    public AuthResponse refresh(String refreshToken){
        Jwt jwt = jwtService.parseToken(refreshToken);
        String email = jwt.getEmailFromClaims();

        User user =  userRepository.findByEmail(email).orElseThrow();
        Map<String, Object> claims = setClaims(user);

        UserDTO userDTO = userMapper.toDTO(user);

        Jwt accessToken = jwtService.generateToken(user.getId(), claims);

        return new AuthResponse(userDTO, accessToken.toString());
    }


    @Transactional
    public MessageResponse forgotPassword(String email){
        userRepository.findByEmail(email).ifPresent(user -> {
            passwordResetTokenRepository.deleteByUser(user);

            String token = UUID.randomUUID().toString();
            PasswordResetToken passwordResetToken = new PasswordResetToken(user, token, resetExpiryMinutes);
            passwordResetTokenRepository.save(passwordResetToken);

            MailBody mailBody = new MailBody(email, token, "Reset Password" );
            emailService.sendPasswordResetEmail(mailBody);
        });

        return new MessageResponse("If that email is registered, you will receive a password reset link shortly.");
    }

    public MessageResponse resetPassword(ResetPasswordRequest request){
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(
                request.getToken()).orElseThrow(() -> new InvalidTokenException("Invalid token"));

        if(resetToken.isExpired()){
            throw new InvalidTokenException("Token has expired");
        }

        if(resetToken.isUsed()){
            throw new InvalidTokenException("Token has been used");
        }

        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        resetToken.setUsed(true);
        passwordResetTokenRepository.save(resetToken);

        return new MessageResponse("Password reset successfully.");
    }
}
