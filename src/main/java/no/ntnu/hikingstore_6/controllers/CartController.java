package no.ntnu.hikingstore_6.controllers;

import no.ntnu.hikingstore_6.dtos.AddItemToCardDTO;
import no.ntnu.hikingstore_6.dtos.DeleteFromCartDTO;
import no.ntnu.hikingstore_6.entities.Cart;
import no.ntnu.hikingstore_6.entities.Product;
import no.ntnu.hikingstore_6.entities.ProductInCart;
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
import java.util.Set;

@RequestMapping("/api/cart")
@RestController
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

    @GetMapping("")
    @RolesAllowed("ROLE_CUSTOMER")
    public Set<ProductInCart> listProductsInCart(@RequestHeader("Authorization")
                                                 String authorization) {

        Integer userID = this.getUserID(authorization);
        Cart cart = this.cartService.getCart(userID);
        return cart.getProducts();
    }



    /**
     * Adds one product to the users cart. Checks userID from jwt to add to right cart.
     *
     * @param authorization jwt to check
     * @param requestBody
     * @return
     */
    @PostMapping("/add")
    @RolesAllowed("ROLE_CUSTOMER")
    public ResponseEntity<Product> addItemToCart(
            @RequestHeader("Authorization") String authorization,
            @RequestBody AddItemToCardDTO requestBody) {

        int userID = this.getUserID(authorization);
        this.cartService.addProductToCart(userID,requestBody.createProduct());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @DeleteMapping
    @RolesAllowed("ROLE_CUSTOMER")
    public ResponseEntity<?> deleteProductFromCart(
            @RequestHeader("Authorization") String authorization,
            @RequestBody DeleteFromCartDTO requestBody) {
        int userID = this.getUserID(authorization);
        ResponseEntity<?> response;
        try {
            this.cartService.removeItemFromCart(userID,requestBody.getCartitemID());
            response = new ResponseEntity<>(HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>("No product with that id found", HttpStatus.NOT_FOUND);
        }
        return response;
    }



    /**
     * Extracts the user ID from subject of jwt in header
     * @param jwtHeader the full jwt with header.
     * @return userID
     */
    private Integer getUserID(String jwtHeader) {
        String jwt = jwtHeader.substring(7);
        return jwtTokenUtil.extractUserID(jwt);
    }



}
