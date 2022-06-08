package no.ntnu.hikingstore_6.repositories;

import no.ntnu.hikingstore_6.entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    public Integer countById(Integer id);
    
    Optional<Product> findById(Integer id);

    @Query(value = "SELECT * FROM products WHERE product_name = ?", nativeQuery = true)
    Product findAllByProductName(String name);

    @Query(value = "SELECT * FROM products WHERE product_category = ?", nativeQuery = true)
    Product findAllByProductCategory(String category);
}
