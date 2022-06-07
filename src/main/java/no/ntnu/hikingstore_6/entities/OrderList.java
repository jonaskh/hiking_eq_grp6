package no.ntnu.hikingstore_6.entities;



import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class OrderList  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;


    /*
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "order")
    private Set<ProductInCart> products = new HashSet<>();

     */

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "orderList")
    private Cart cart = new Cart();


    @Column(nullable = false, name = "user_email")
    private String userEmail;

    @Column(nullable = false, name = "user_address")
    private String userAddress;

    @Column(nullable = false, name = "user_zipcode")
    private Integer userZipCode;


    @Column(nullable = false, name = "total_order_price")
    private BigDecimal totalOrderPrice;



    @Column(nullable = false, name = "order_status")
    private OrderStatus orderStatus;

    @CreationTimestamp
    private LocalDateTime createTime;

    public OrderList(User user) {
        this.userEmail = user.getEmail();
        this.userAddress = user.getAddress();
        this.userZipCode = user.getZipcode();
        this.totalOrderPrice = cart.getProducts().stream().map(item ->
                new BigDecimal(item.getPrice()).multiply(new BigDecimal(item.getPrice())))
                .reduce(BigDecimal::add).orElse(new BigDecimal((0)));
        this.orderStatus = OrderStatus.SENT;
    }

    public OrderList() {

    }

    public String getUserEmail() {
        return userEmail;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    /*
    public Set<ProductInCart> getProducts() {
        return products;
    }



    public void setProducts(Set<ProductInCart> products) {
        this.products = products;
    }
     */

    public enum OrderStatus {
        /*
        Status: Sent
        Default status for order
         */
        SENT,

        /*
        Status: Canceled
        Admin can cancel an order
         */
        CANCELED
    }



}
