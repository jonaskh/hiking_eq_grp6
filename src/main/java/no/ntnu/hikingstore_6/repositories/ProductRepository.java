package no.ntnu.hikingstore_6.repositories;

import no.ntnu.hikingstore_6.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    public Long countById(Integer id);
    Product findProductById(Integer id);

}
