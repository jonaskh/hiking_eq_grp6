package no.ntnu.hikingstore_6.repositories;

import no.ntnu.hikingstore_6.entities.ProductInCart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import no.ntnu.hikingstore_6.entities.Product;

import java.util.Optional;

@Repository
public interface ProductInCartRepository extends CrudRepository<ProductInCart,Long> {

    Optional<ProductInCart> findProductInCart(Long cartID, long productID);

}
