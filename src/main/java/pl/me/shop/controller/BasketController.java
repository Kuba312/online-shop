package pl.me.shop.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.me.shop.model.dao.Basket;
import pl.me.shop.model.dao.Product;
import pl.me.shop.service.BasketService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/basket")
public class BasketController {

    private final BasketService basketService;

    @GetMapping
    public List<Basket> getUserBasket() {
        return basketService.getUserBasket();
    }

    @PostMapping
    public Basket addProductToBasket(@RequestBody Product product) {
        return basketService.addToBasket(product);
    }


    @DeleteMapping("/{id}")
    public void deleteProductFromBasketById(@PathVariable Long id) {
        basketService.deleteProductFromBasket(id);
    }


    @DeleteMapping
    public void deleteUserBasket() {
        basketService.deleteUserBasket();
    }

}
