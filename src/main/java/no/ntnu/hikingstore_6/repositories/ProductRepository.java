package no.ntnu.hikingstore_6.repositories;

import no.ntnu.hikingstore_6.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    public Long countById(Integer id);
    public List<Product> findProductsByProductCategory(String productCategory);

}
