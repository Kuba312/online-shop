package pl.me.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.me.shop.model.dao.User;

import java.util.Optional;

//Każde repo, które jest odpowiedzalne z bazą danych sql musi dzieziczyć po JpaRepository, który jest wykorzystywany w serwisie
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

    Optional<User> findByMail(String mail);

    Optional<User> findByActivatedCode(String activatedCode);
}
