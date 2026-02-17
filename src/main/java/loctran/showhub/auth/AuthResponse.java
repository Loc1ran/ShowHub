package loctran.showhub.auth;

import loctran.showhub.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private UserDTO user;
    private String jwtToken;
}
