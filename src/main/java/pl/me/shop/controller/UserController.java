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


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        
        
        return userMapper.userToUserDto(userService.getById(id));
    }

    
    @GetMapping
    public Page<UserDto> getPageUser(@RequestParam Integer page, @RequestParam Integer size) {
        
        
        
        
        return userService.getPage(PageRequest.of(page, size))
                .map(userMapper::userToUserDto);
    }

    @PostMapping
    public UserDto saveUser(@RequestBody UserDto userDto) {
        return userMapper.userToUserDto(userService.save(userMapper.userDtoToUser(userDto)));
    }

    @DeleteMapping
    public void deleteUser(@RequestParam Long id) {
        userService.deleteById(id);
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
