package loctran.showhub.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void updateProfilePicture(UserDetails userDetails, String imageUrl){
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(
                () -> new ResourceNotFound("Invalid User")
        );

        user.setProfilePicUrl(imageUrl);
        userRepository.save(user);
    }
}
