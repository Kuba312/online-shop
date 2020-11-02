package pl.me.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.me.shop.model.dao.Basket;
import pl.me.shop.model.dao.Orders;
import pl.me.shop.service.OrderService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public void addProductsFromBasketToOrder() {
        orderService.addProductsFromBasketToOrder();
    }


}
