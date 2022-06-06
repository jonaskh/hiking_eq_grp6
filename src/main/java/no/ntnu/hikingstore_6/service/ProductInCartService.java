package no.ntnu.hikingstore_6.service;


import no.ntnu.hikingstore_6.entities.ProductInCart;
import no.ntnu.hikingstore_6.entities.User;

public interface ProductInCartService {
    void update(String itemId, Integer quantity, User user);

    ProductInCart findOne(String itemId, User user);
}
