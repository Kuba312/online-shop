package pl.me.shop.service.impl;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import org.mockito.stubbing.OngoingStubbing;
import pl.me.shop.model.dao.Basket;
import pl.me.shop.model.dao.Orders;
import pl.me.shop.model.dao.Product;
import pl.me.shop.repository.BasketRepository;
import pl.me.shop.repository.OrderRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderServiceImpl;

    @InjectMocks
    private OrderRepository orderRepository;

    @InjectMocks
    private BasketRepository basketRepository;

    @InjectMocks
    private BasketServiceImpl basketServiceImpl;


//    public void addProductsFromBasketToOrder() {
//        List<Product> collect = basketServiceImpl.getUserBasket().stream()
//                .map(Basket::getProduct)
//                .collect(Collectors.toList());
//
//        collect.forEach(product1 -> orderRepository.findById(product1.getId())
//                .ifPresent(order -> {
//                    order.setId(UUID.randomUUID().toString());
//                    Orders order1 = orderRepository.save(order);
//                    basketServiceImpl.deleteUserBasket();
//                }));
//    }

    @Test
    void addProductsFromBasketToOrder() {




    }
}
