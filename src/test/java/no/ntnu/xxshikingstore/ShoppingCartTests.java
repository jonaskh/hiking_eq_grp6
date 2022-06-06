package no.ntnu.xxshikingstore;


import no.ntnu.hikingstore_6.entities.CartItem;
import no.ntnu.hikingstore_6.entities.Product;
import no.ntnu.hikingstore_6.entities.User;
import no.ntnu.hikingstore_6.repositories.CartItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ShoppingCartTests {

    @Autowired
    private CartItemRepository cartrepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testAddOneCartItem() {
        Product product1 = entityManager.find(Product.class, 2);
        Product product2 = entityManager.find(Product.class, 3);
        User user = entityManager.find(User.class, 15);

        CartItem newItem1 = new CartItem();
        CartItem newItem2 = new CartItem();

        newItem1.setProduct(product1);
        newItem1.setUser(user);
        newItem1.setQuantity(1);

        newItem1.setProduct(product2);
        newItem1.setUser(user);
        newItem1.setQuantity(2);

        CartItem savedCartItem1 = cartrepo.save(newItem1);
        CartItem savedCartItem2 = cartrepo.save(newItem2);

        Assertions.assertTrue(savedCartItem1.getId() > 0);
        Assertions.assertTrue(savedCartItem2.getId() > 0);

    }


    @Test
    public void testGetItemByUser() {
        User user = new User();
        user.setId(15);

        List<CartItem> cartItems = cartrepo.findByUser(user);

        Assertions.assertEquals(2, cartItems.size());
    }


}
