package loctran.showhub.mappers;

import loctran.showhub.dto.UserDTO;
import loctran.showhub.dto.UserRegisterRequest;
import loctran.showhub.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserRegisterRequest userRegisterRequest);
    UserDTO toDTO(User user);
}
