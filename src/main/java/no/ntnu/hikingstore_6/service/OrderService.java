package no.ntnu.hikingstore_6.service;

import no.ntnu.hikingstore_6.entities.Cart;
import no.ntnu.hikingstore_6.entities.OrderList;
import no.ntnu.hikingstore_6.entities.Product;
import no.ntnu.hikingstore_6.entities.ProductInCart;
import no.ntnu.hikingstore_6.entities.ProductInOrder;
import no.ntnu.hikingstore_6.entities.User;
import no.ntnu.hikingstore_6.exceptions.ProductNotFoundException;
import no.ntnu.hikingstore_6.exceptions.UserNotFoundException;
import no.ntnu.hikingstore_6.repositories.OrderRepository;
import no.ntnu.hikingstore_6.repositories.ProductInCartRepository;
import no.ntnu.hikingstore_6.repositories.ProductRepository;
import no.ntnu.hikingstore_6.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    ProductInCartRepository productInCartRepository;

    @Autowired
    UserService userService;

    @Autowired
    ShoppingCartService shoppingCartService;

    public List<OrderList> findAllByUserId(Integer id) {
        return orderRepository.findAllOrdersByUserId(id);
    }

    public OrderList findOrderById(Integer id) { return orderRepository.findByOrderId(id); }

    @Transactional
    public OrderList cancel(Integer orderId) {
        OrderList orderList = orderRepository.findByOrderId(orderId);

        orderList.setOrderStatus(OrderList.OrderStatus.CANCELED);
        //orderRepository.save(orderList);

        // Restore Stock
        Iterable<ProductInCart> products = orderList.getCart().getProducts();
        for(ProductInCart productInCart : products) {
            Optional<Product> optionalProduct = productRepository.findById(productInCart.getProductId());
            if(optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                try {
                    productService.increaseAmount(productInCart.getProductId(), productInCart.getProductAmount());
                } catch (ProductNotFoundException e) {
                    //Do something
                }
            }
        }
        return orderRepository.findByOrderId(orderId);

    }

    public void addOrder(Integer id) {

        Cart cart = shoppingCartService.getCart(id);
        try {
            User user = userService.get(id);
            OrderList orderList = new OrderList();
            orderList.setUser(user);
            orderRepository.save(orderList);
            float totalPrice = 0;

            for (ProductInCart productInCart : cart.getProducts()) {

                ProductInOrder productInOrder = new ProductInOrder(productInCart);
                productInOrder.setOrderList(orderList);
                orderRepository.save(orderList);


                orderList.addOrderItem(productInOrder);
                productInCartRepository.delete(productInCart);

            }

            orderList.setTotalOrderPrice();

            orderList.setOrderStatus(OrderList.OrderStatus.SENT);

            orderRepository.save(orderList);

            user.addOrder(orderList);


            userRepository.save(user);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }


    }
}
