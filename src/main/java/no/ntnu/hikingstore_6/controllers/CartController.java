package no.ntnu.hikingstore_6.controllers;

import no.ntnu.hikingstore_6.dtos.AddItemToCardDTO;
import no.ntnu.hikingstore_6.entities.Cart;
import no.ntnu.hikingstore_6.repositories.ProductInCartRepository;
import no.ntnu.hikingstore_6.security.JwtTokenUtil;
import no.ntnu.hikingstore_6.service.ProductService;
import no.ntnu.hikingstore_6.service.ShoppingCartService;
import no.ntnu.hikingstore_6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RequestMapping("/api/cart")
@RestController
@CrossOrigin
public class CartController {

    @Autowired
    ShoppingCartService cartService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    ProductInCartRepository productInCartRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;


    public ResponseEntity<Cart> getuserCart(@RequestHeader("Authorization")
                                                    String authorization) {
        ResponseEntity response;
        Long userID = this.getUserID(authorization);

        Cart cart = this.cartService.getCart(userID);
        if (cart == null) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            response = new ResponseEntity<>(HttpStatus.OK);
        }
        return response;

    }


    /**
     * Adds one product to the users cart. Checks userID from jwt to add to right cart.
     *
     * @param authorization jwt to check
     * @param requestBody
     * @return
     */
    @PostMapping("/add")
    @RolesAllowed("{ROLE_CUSTOMER}")
    public ResponseEntity<?> addItemToCart(
            @RequestHeader("Authorization") String authorization,
            @RequestBody AddItemToCardDTO requestBody) {

        long userID = this.getUserID(authorization);
        this.cartService.addProductToCart(userID,requestBody.createProduct());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * Extracts the user ID from subject of jwt in header
     * @param jwtHeader the full jwt with header.
     * @return userID
     */
    private long getUserID(String jwtHeader) {
        String jwt = jwtHeader.substring(7);
        return jwtTokenUtil.extractUserID(jwt);
    }

}
