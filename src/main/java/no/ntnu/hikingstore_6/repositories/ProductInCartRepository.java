package no.ntnu.hikingstore_6.repositories;

import no.ntnu.hikingstore_6.entities.ProductInCart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Repository;
import no.ntnu.hikingstore_6.entities.Product;

import java.util.Optional;

@Repository
public interface ProductInCartRepository extends CrudRepository<ProductInCart,Integer> {


    @Query(value = "select * from product_in_cart where cart_id = ? and product_id = ?", nativeQuery = true)
    Optional<ProductInCart> findProductInCart(Integer cartID, Integer productID);

}
