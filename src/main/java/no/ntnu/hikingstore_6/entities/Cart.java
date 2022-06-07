package no.ntnu.hikingstore_6.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Integer cartID;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "cart")
    private User user;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, mappedBy = "cart")
    @Column(name = "product_id_in_cart")
    private Set<ProductInCart> productsInCart = new HashSet<>();


    public Cart(User user) {
        this.user = user;
    }

    public Cart() {
    }

    public Cart(int cartID, User user, Set<ProductInCart> productsInCart) {
        this.cartID = cartID;
        this.user = user;
        this.productsInCart = productsInCart;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCartID() {
        return cartID;
    }


    public Set<ProductInCart> getProducts() {
        return productsInCart;
    }

    public void addProductToCart(ProductInCart productInCart) {
        this.productsInCart.add(productInCart);
    }





    @Override
    public String toString() {
       return "Cart{" + "cartID=" + cartID +
                ", products= " + productsInCart + '}';
    }


}
