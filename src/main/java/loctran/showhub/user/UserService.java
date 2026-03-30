package loctran.showhub.user;


import loctran.showhub.dto.UserRegisterRequest;
import loctran.showhub.exceptions.BadRequestException;
import loctran.showhub.mappers.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public void updateProfilePicture(UserDetails userDetails, String imageUrl){
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(
                () -> new ResourceNotFound("Invalid User")
        );

        user.setProfilePicUrl(imageUrl);
        userRepository.save(user);
    }

    public User registerUser(UserRegisterRequest request){
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

        return user;
    }
}
