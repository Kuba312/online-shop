package pl.me.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pl.me.shop.model.dao.Orders;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    @Query(value = "select sum(p.price*o.quantity) as totalPrice, count(o.quantity) as counter, o.order_number as orderNumber " +
            "from product p " +
            "join orders o " +
            "on  p.id = o.product_id " +
            "join user u " +
            "on u.id = o.user_id " +
            "where o.user_id =?1;", nativeQuery = true)
    //Przekazuje wartosci z selecta do tego interfejsu
    List<GroupedOrders> getOrdersForCurrentUser(Long userId);
    void getOrdersByOrderNumber(String orderNumber);

    //Używamy jesli nie uzywamy standarowej klasy tabelki
    interface GroupedOrders {
        Double getTotalPrice();

        void setTotalPrice();

        Long getCounter();

        void setCounter();

        String getOrderNumber();

        void setOrderNumber();
    }
}
