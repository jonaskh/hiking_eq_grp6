package no.ntnu.hikingstore_6.service;

import no.ntnu.hikingstore_6.entities.Cart;
import no.ntnu.hikingstore_6.entities.OrderList;
import no.ntnu.hikingstore_6.entities.Product;
import no.ntnu.hikingstore_6.entities.ProductInCart;
import no.ntnu.hikingstore_6.entities.ProductInOrder;
import no.ntnu.hikingstore_6.entities.User;
import no.ntnu.hikingstore_6.exceptions.ProductNotFoundException;
import no.ntnu.hikingstore_6.exceptions.UserNotFoundException;
import no.ntnu.hikingstore_6.repositories.*;
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
    ProductInOrderRepository productInOrderRepository;

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
        Iterable<ProductInCart> products = orderList.getUser().getCart().getProducts();
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

    public void addOrder(Integer userId) {

        try {
            User user = userService.get(userId);
            Cart cart = user.getCart();
            OrderList orderList = new OrderList();
            orderList.setUser(user);
            orderList.setOrderStatus(OrderList.OrderStatus.SENT);
            orderRepository.save(orderList);
            float totalPrice = 0;

            if (!cart.getProducts().isEmpty()) {
                for (ProductInCart productInCart : cart.getProducts()) {

                    ProductInOrder productInOrder = new ProductInOrder(productInCart);
                    productInOrder.setOrderList(orderList);
                    productInOrderRepository.save(productInOrder);

                    orderList.addOrderItem(productInOrder);
                    productInCartRepository.delete(productInCart);
                    productInCart.setCart(null);
                    cart.removeProduct(productInCart);

                }
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
