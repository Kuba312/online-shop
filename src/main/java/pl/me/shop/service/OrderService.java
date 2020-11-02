package pl.me.shop.service;

import pl.me.shop.repository.OrderRepository;


import java.util.List;

public interface OrderService {


    void addProductsFromBasketToOrder();

    List<OrderRepository.GroupedOrders> getOrders();

    void getOrdersDetails(String orderNumber);

}
