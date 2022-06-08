package no.ntnu.hikingstore_6.repositories;

import no.ntnu.hikingstore_6.entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    Integer countById(Integer id);
    Product findProductById(Integer id);

    @Query(value = "SELECT * FROM products WHERE product_name = ?", nativeQuery = true)
    Product findAllByProductName(String name);

    @Query(value = "SELECT * FROM products WHERE product_category = ?", nativeQuery = true)
    Product findAllByProductCategory(String category);

}
