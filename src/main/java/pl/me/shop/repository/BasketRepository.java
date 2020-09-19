package pl.me.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.me.shop.model.dao.Basket;
import pl.me.shop.model.dao.Product;

import java.util.List;
import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Long> {

    Optional<Basket> findByProductIdAndUserId(Long productId, Long userId);

    void deleteByUserLogin(String Login);

    List<Basket> getBasketByUserName(String name);

    void deleteByUserLoginAndProductId(String login, Long id);



}
