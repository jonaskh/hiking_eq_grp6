package no.ntnu.hikingstore_6.service;


import no.ntnu.hikingstore_6.entities.Cart;
import no.ntnu.hikingstore_6.entities.Product;
import no.ntnu.hikingstore_6.entities.ProductInCart;
import no.ntnu.hikingstore_6.entities.User;
import no.ntnu.hikingstore_6.repositories.CartItemRepository;
import no.ntnu.hikingstore_6.repositories.CartRepository;
import no.ntnu.hikingstore_6.repositories.ProductInCartRepository;
import no.ntnu.hikingstore_6.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartService {

    @Autowired
    ProductInCartRepository productInCartRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductService productService;


    private int getUserId(User user)  {
        return user.getId();
    }

    /**
     * Finds user ID and then the cart id that belongs to that user, and returns the cart.
     * @param id
     * @return
     */
    public Cart getCart(Long id) {
        Cart cart = new Cart();
        Optional<Cart> optionalCart = cartRepository.findById(this.getCartID(id));

        if (optionalCart.isPresent()) {
            cart = optionalCart.get();
        }
        return cart;
    }

    public void addProductToCart(Long userid, Product product) {
        ProductInCart productToAdd;
        Optional<ProductInCart> optionalProduct = this.productInCartRepository.findProductInCart(this.getCartID(userid),product.getId());

        if(optionalProduct.isPresent()) {
            productToAdd = optionalProduct.get();
            productToAdd.incrementAmount();
            this.productInCartRepository.save(productToAdd);
        } else {
            productToAdd = new ProductInCart(product);
            Cart cart = this.getCart(userid);
            productToAdd.setCart(cart);
            cart.addProductToCart(productToAdd);

            this.productInCartRepository.save(productToAdd);
            this.cartRepository.save(cart);
        }

    }


    public Long getCartID(Long userID) {

        return this.userRepository.findCartID(userID);
    }

}
