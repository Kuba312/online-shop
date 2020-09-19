package pl.me.shop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.me.shop.model.dao.User;
import pl.me.shop.model.dto.RemindPassword;

public interface UserService {

    User save(User user);

    User getById(Long id);

    void deleteById(Long id);

    Page<User> getPage(Pageable pageable);

    void remindPassword(RemindPassword remindPassword);

    void restartPassword(String activatedCode, RemindPassword remindPassword);
}
