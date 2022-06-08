package no.ntnu.hikingstore_6.repositories;

import no.ntnu.hikingstore_6.entities.Cart;
import no.ntnu.hikingstore_6.entities.OrderList;
import no.ntnu.hikingstore_6.entities.User;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<OrderList, Integer> {

    public List<OrderList> findByUser(User user);

    OrderList findByOrderId(Integer id);

    //List<OrderList> findAllByOrderStatusOrderByCreateTimeDesc(OrderList.OrderStatus orderStatus);

    //List<OrderList> findAllByUserEmailOrderByOrderStatusAscCreateTimeDesc(String buyerEmail);

    @Query(value = "SELECT * FROM orders WHERE order_status = ?", nativeQuery = true)
    List<OrderList> getOrderStatusSent(OrderList.OrderStatus status);

    //@Query("SELECT m FROM OrderList m WHERE m.orderStatus = OrderList.OrderStatus.CANCELED")
    //List<OrderList> getOrderStatusCanceled(Pageable pageable);

    @Query(value = " SELECT * FROM orders WHERE user_id = ?", nativeQuery = true)
    List<OrderList> findAllOrdersByUserId(Integer id);

}
