package pl.me.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.me.shop.model.dao.Basket;
import pl.me.shop.model.dao.Orders;
import pl.me.shop.model.dao.User;
import pl.me.shop.repository.OrderRepository;
import pl.me.shop.service.BasketService;
import pl.me.shop.service.OrderService;
import pl.me.shop.service.UserService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;
    private final BasketService basketService;
    private final UserService userService;


    @Transactional
    @Override
    public void addProductsFromBasketToOrder() {
        List<Basket> userBasket = basketService.getUserBasket();
        Orders orders1 = new Orders();
        User user = new User();
        orders1.setOrderNumber(UUID.randomUUID().toString());
        for (Basket basket : userBasket) {

            orders1.setProduct(basket.getProduct());
            orders1.setUser(user);
            orders1.setQuantity(basket.getQuantity());

            orderRepository.save(orders1);
        }
        basketService.deleteUserBasket();

        System.out.println("Items had been added to order. Your basket is empty.");
    }

    @Override
    public List<OrderRepository.GroupedOrders> getOrders() {
        return orderRepository.getOrdersForCurrentUser(userService.getCurrentUser().getId());
    }

    @Override
    public void getOrdersDetails(String orderNumber) {
        orderRepository.getOrdersByOrderNumber(orderNumber);
    }


}


