package pl.me.shop.controller;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import pl.me.shop.mapper.UserMapper;
import pl.me.shop.model.dto.RemindPassword;
import pl.me.shop.model.dto.UserDto;
import pl.me.shop.service.UserService;
import pl.me.shop.service.impl.UserServiceImpl;

//Używany jest do komunikacji z użytkownikiem
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {


    private final UserService userService;

    private final UserMapper userMapper;

    //Pobiera użytkownika
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        //(Od prawej do lewej) Wykonywana jest na początku funkcja getById z serwisu, następnie ta funkcja zwraca usera, który jest przekazywany
        //do funkcji userToUserDto, która mapuje z usera na useraDto
        return userMapper.userToUserDto(userService.getById(id));
    }

    //Pagowanie zwraca stronę z danymi
    @GetMapping

    public Page<UserDto> getPageUser(@RequestParam Integer page, @RequestParam Integer size) {
        //38 linia wywołuje sie od prawej do lewej. Najpierw wywołana jest statyczna funkcja of i tworzy ona obiekt pageable,
        // który ma dane na temat strony jaka chcemy pobrać z bazy danych
        //nastepnie pageable jest przekazywany do funkcji getPage, która zwraca page user (strona z danymi z bazy danych)
        //Na poge user jest wywoływana funkcja map, która mappuje usera na useraDto dla każdego elementu w page'u(bogatsza lista)
        return userService.getPage(PageRequest.of(page, size))
                .map(userMapper::userToUserDto);
    }
//To samo tylko dla produktu!!

    @PostMapping
    public UserDto saveUser(@RequestBody UserDto userDto) {
        return userMapper.userToUserDto(userService.save(userMapper.userDtoToUser(userDto)));
    }

    @DeleteMapping
    public void deleteUser(@RequestParam Long id) {
        userService.getById(id);
    }

    @PutMapping
    public void remindPassword(@RequestBody RemindPassword remindPassword) {
        userService.remindPassword(remindPassword);
    }

    @PutMapping("/{activatedCode}")
    public void restartPassword(@PathVariable String activatedCode, @RequestBody RemindPassword remindPassword) {
        userService.restartPassword(activatedCode, remindPassword);

    }

}
