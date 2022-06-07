package no.ntnu.hikingstore_6.controllers;

import no.ntnu.hikingstore_6.entities.OrderList;
import no.ntnu.hikingstore_6.exceptions.OrderAlreadyCanceledException;
import no.ntnu.hikingstore_6.repositories.OrderRepository;
import no.ntnu.hikingstore_6.service.OrderService;
import no.ntnu.hikingstore_6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    OrderRepository orderRepository;

    @PatchMapping("/order/cancel/{id}")
    public ResponseEntity<OrderList> cancel(@PathVariable("id") Integer orderId, Authentication authentication) {
        OrderList orderMain = orderService.findOrderById(orderId);
        if (!authentication.getName().equals(orderMain.getUserEmail()) && authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CUSTOMER"))) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(orderService.cancel(orderId));


    }

    @GetMapping("/order")
    public List<OrderList> orderList(Integer id) {
        List<OrderList> orderList;
        orderList = orderService.findAllByUserId(id);

        return orderList;
    }
}
