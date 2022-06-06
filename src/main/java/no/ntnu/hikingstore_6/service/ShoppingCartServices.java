package no.ntnu.hikingstore_6.service;


import no.ntnu.hikingstore_6.entities.CartItem;
import no.ntnu.hikingstore_6.entities.User;
import no.ntnu.hikingstore_6.repositories.CartItemRepository;
import no.ntnu.hikingstore_6.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServices {

    @Autowired
    CartItemRepository cartRepo;

    @Autowired
    UserRepository userRepository;

    public List<CartItem> listCartItems(int id){

        Cart ca
    }

    private int getUser(int userID) {

    }

}
