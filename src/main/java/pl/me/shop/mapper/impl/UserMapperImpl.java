package pl.me.shop.mapper.impl;

import org.springframework.stereotype.Component;
import pl.me.shop.mapper.UserMapper;
import pl.me.shop.model.dao.User;
import pl.me.shop.model.dto.UserDto;

//Funkcje, których używamy do mapowania z useraDto na Usera i odwrotnie. Ma nie wystawiac dokładnych danych do użytkownika w celu większego
//bezpieczeństwa.
@Component
public class UserMapperImpl implements UserMapper {

    //Z bazy danych do użytkownika (dlatego nie przekazujemy hasła)
    @Override
    public UserDto userToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .mail(user.getMail())
                .login(user.getLogin())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    //Tutaj na odwórt
    @Override
    public User userDtoToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getLogin())
                .mail(userDto.getMail())
                .login(userDto.getLogin())
                .phoneNumber(userDto.getPhoneNumber())
                .password(userDto.getPassword())
                .build();
    }
}
