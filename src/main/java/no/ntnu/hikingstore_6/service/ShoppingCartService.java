package no.ntnu.hikingstore_6.service;


import no.ntnu.hikingstore_6.dtos.AddItemToCardDTO;
import no.ntnu.hikingstore_6.entities.Cart;
import no.ntnu.hikingstore_6.entities.OrderList;
import no.ntnu.hikingstore_6.entities.Product;
import no.ntnu.hikingstore_6.entities.ProductInCart;
import no.ntnu.hikingstore_6.entities.User;
import no.ntnu.hikingstore_6.exceptions.ProductNotFoundException;
import no.ntnu.hikingstore_6.repositories.CartRepository;
import no.ntnu.hikingstore_6.repositories.OrderRepository;
import no.ntnu.hikingstore_6.repositories.ProductInCartRepository;
import no.ntnu.hikingstore_6.repositories.ProductRepository;
import no.ntnu.hikingstore_6.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    ProductRepository productRepository;


    private int getUserId(User user)  {
        return user.getId();
    }

    /**
     * Finds user ID and then the cart id that belongs to that user, and returns the cart.
     * @param id
     * @return
     */
    public Cart getCart(Integer id) {
        Cart cart = new Cart();
        Optional<Cart> optionalCart = cartRepository.findById(this.getCartID(id));

        if (optionalCart.isPresent()) {
            cart = optionalCart.get();
        }
        return cart;
    }

    public void addProductToCart(Integer userid, Product product) {
        ProductInCart productToAdd;
        Optional<Product> optionalProduct = this.productRepository.findById(product.getId());

        if(optionalProduct.isPresent() && productInCartRepository.findById(product.getId()).isPresent()) {
            productToAdd = productInCartRepository.findProductInCart(userid,product.getId()).get();
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

    public void removeItemFromCart(int userId, int cartItemId) {
        Optional<ProductInCart> result = this.productInCartRepository.findById(cartItemId);
        if (result.isPresent()) {
            ProductInCart cartItem = result.get();
                cartItem.decreaseAmount();
                this.productInCartRepository.save(cartItem);
                this.productInCartRepository.delete(cartItem);
                Cart cart = this.getCart(userId);
                cart.removeProduct(cartItem);
                this.cartRepository.save(cart);
            }
        }



    public Integer getCartID(Integer userID) {

        return this.userRepository.findCartID(userID);
    }



}
