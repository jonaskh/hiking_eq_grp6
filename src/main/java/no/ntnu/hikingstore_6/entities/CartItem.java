package no.ntnu.hikingstore_6.entities;




import no.ntnu.hikingstore_6.repositories.CartItemRepository;
import no.ntnu.hikingstore_6.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;


//One cart item, to be placed in cart.
@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartItem_id")
    private Integer id;

    @Column(name = "product_id")
    private int productID;

    //number of items
    @Column
    private int quantity;

    //Links to cart using many to one relation.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;


    //Empty constructor
    public CartItem() {
    }

    public CartItem(Integer id, int productID, int quantity) {
        this.id = id;
        this.productID = productID;
        this.quantity = quantity;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                "productID = " + productID +
                ", quantity=" + quantity +
                '}';
    }
}
