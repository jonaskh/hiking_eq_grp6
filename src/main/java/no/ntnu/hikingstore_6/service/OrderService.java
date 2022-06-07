package no.ntnu.hikingstore_6.service;

import no.ntnu.hikingstore_6.entities.OrderList;
import no.ntnu.hikingstore_6.entities.Product;
import no.ntnu.hikingstore_6.entities.ProductInCart;
import no.ntnu.hikingstore_6.exceptions.OrderAlreadyCanceledException;
import no.ntnu.hikingstore_6.exceptions.ProductNotFoundException;
import no.ntnu.hikingstore_6.repositories.OrderRepository;
import no.ntnu.hikingstore_6.repositories.ProductInCartRepository;
import no.ntnu.hikingstore_6.repositories.ProductRepository;
import no.ntnu.hikingstore_6.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Autowired
    ProductInCartRepository productInOrderRepository;

    @Autowired
    UserService userService;

    public List<OrderList> findAllByUserId(Integer id) {
        return orderRepository.findAllOrdersByUserId(id);
    }

    public OrderList findOrderById(Integer id) { return orderRepository.findOne(id); }

    @Transactional
    public OrderList cancel(Integer orderId) {
        OrderList orderList = orderRepository.findOne(orderId);

        orderList.setOrderStatus(OrderList.OrderStatus.CANCELED);
        //orderRepository.save(orderList);

        // Restore Stock
        Iterable<ProductInCart> products = orderList.getCart().getProducts();
        for(ProductInCart productInCart : products) {
            Product product = productRepository.findByProductId(productInCart.getProductId());
            if(product != null) {
                try {
                    productService.increaseAmount(productInCart.getProductId(), productInCart.getProductAmount());
                } catch (ProductNotFoundException e) {
                    //Do something
                }
            }
        }
        return orderRepository.findOne(orderId);

    }
}
