package pl.me.shop.mapper;

import pl.me.shop.model.dao.User;
import pl.me.shop.model.dto.UserDto;

public interface UserMapper {

   UserDto userToUserDto(User user);
   User userDtoToUser(UserDto userDto);
}
