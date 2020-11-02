package pl.me.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.me.shop.model.dao.User;
import pl.me.shop.model.dto.RemindPassword;
import pl.me.shop.repository.RoleRepository;
import pl.me.shop.repository.UserRepository;
import pl.me.shop.service.UserService;

import javax.persistence.EntityNotFoundException;
import java.awt.*;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        roleRepository.findByName("ROLE_USER")
                .ifPresent(role -> user.setRoles(Collections.singleton(role)));
        return userRepository.save(user);
    }

    @Override
    public User getCurrentUser() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByLogin(login).orElseThrow(EntityNotFoundException::new);
    }


    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + "does not exist"));
    }

    @Override
    public void deleteById(Long id) {

        userRepository.deleteById(id);

    }

    @Override
    public Page<User> getPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public void remindPassword(RemindPassword remindPassword) {
        userRepository.findByMail(remindPassword.getMail())
                .ifPresent(user -> {
                    user.setActivatedCode(UUID.randomUUID().toString());
                    userRepository.save(user);
                });
    }

    @Override
    public void restartPassword(String activatedCode, RemindPassword remindPassword) {
        userRepository.findByActivatedCode(activatedCode)
                .ifPresent(user -> {
                    user.setPassword(passwordEncoder.encode(remindPassword.getPassword()));
                    userRepository.save(user);
                });

    };


}
