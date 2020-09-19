package pl.me.shop.service;

import pl.me.shop.model.dao.Basket;
import pl.me.shop.model.dao.Product;

import java.util.List;

public interface BasketService {

    List<Basket> getUserBasket();

    Basket addToBasket(Product product);

    void deleteProductFromBasket(Long id);

    void deleteUserBasket();

}
