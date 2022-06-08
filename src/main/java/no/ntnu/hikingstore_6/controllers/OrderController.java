package no.ntnu.hikingstore_6.controllers;

import no.ntnu.hikingstore_6.dtos.AddItemToCardDTO;
import no.ntnu.hikingstore_6.entities.OrderList;
import no.ntnu.hikingstore_6.entities.User;
import no.ntnu.hikingstore_6.exceptions.OrderAlreadyCanceledException;
import no.ntnu.hikingstore_6.exceptions.UserNotFoundException;
import no.ntnu.hikingstore_6.repositories.OrderRepository;
import no.ntnu.hikingstore_6.security.JwtTokenUtil;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RequestMapping("/api/order")
@RestController
@CrossOrigin
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/add")
    @CrossOrigin
    @RolesAllowed("{ROLE_CUSTOMER}")
    public ResponseEntity<?> addOrder (@RequestHeader("Authorization") String authorization) {
        try {
            User user = userService.get(this.getUserID(authorization));
            orderService.addOrder(user.getCartID());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>("user not found", HttpStatus.BAD_REQUEST);
        }

    }

    private Integer getUserID(String jwtHeader) {
        String jwt = jwtHeader.substring(7);
        return jwtTokenUtil.extractUserID(jwt);
    }
}
