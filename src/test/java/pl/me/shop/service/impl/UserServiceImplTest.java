package pl.me.shop.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import pl.me.shop.controller.UserController;
import pl.me.shop.model.dao.Role;
import pl.me.shop.model.dao.User;
import pl.me.shop.model.dto.RemindPassword;
import pl.me.shop.repository.RoleRepository;
import pl.me.shop.repository.UserRepository;
import pl.me.shop.service.UserService;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {


    @InjectMocks
    private UserServiceImpl userServiceImpl;


    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserController userController;
    @Mock
    private UserService userService;

    @Test
    void saveTest() {
        //given
        User user = new User();
        user.setPassword("kuba123");
        Mockito.when(passwordEncoder.encode("kuba123")).thenReturn("cokolwiek");
        Mockito.when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(new Role(1L, "ROLE_USER")));
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(new User());

        //when
        User result = userServiceImpl.save(user);


        //then
        assertEquals(new User(), result);

    }

    @Test
    void getUserByIdTest() {
        //given
        User user = new User();
        user.setId(1L);
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        //when
        User userService = userServiceImpl.getById(1L);


        //then
        assertEquals(1L, userService.getId().longValue());

    }

    @Test
    void deleteUserTest() {
        //given
        User user = new User();
        user.setId(1L);

        //when
        userServiceImpl.deleteById(1L);

        //then
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(1L);
    }

    //    public void remindPassword(RemindPassword remindPassword) {
//        userRepository.findByMail(remindPassword.getMail()).ifPresent(user -> {
//            user.setActivatedCode(UUID.randomUUID().toString());
//            userRepository.save(user);
//        });
//    }
    @Test
    void remindPasswordTest() {
        User user = new User();
        RemindPassword remindPassword = new RemindPassword();
        user.setMail(user.getMail());

        Mockito.when(userRepository.findByMail(Mockito.any())).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(new User());

        userServiceImpl.remindPassword(remindPassword);

        Mockito.verify(userRepository, Mockito.times(1)).findByMail(user.getMail());

    }

    //    public void restartPassword(String activatedCode, RemindPassword remindPassword) {
//        userRepository.findByActivatedCode(activatedCode).ifPresent(user ->
//        {
//            user.setPassword(passwordEncoder.encode(remindPassword.getPassword()));
//            userRepository.save(user);
//        });
//    }
    @Test
    void restartPasswordTest() {
        //given
        RemindPassword remindPassword = new RemindPassword();
        User user = new User();
        user.setActivatedCode(user.getActivatedCode());

        Mockito.when(userRepository.findByActivatedCode(Mockito.any())).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(new User());

        //when
        userServiceImpl.restartPassword(user.getActivatedCode(), remindPassword);

        //then
        Mockito.verify(userRepository, Mockito.times(1)).findByActivatedCode(user.getActivatedCode());

    }
}


