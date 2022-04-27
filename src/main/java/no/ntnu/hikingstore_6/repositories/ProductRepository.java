package no.ntnu.hikingstore_6.repositories;

import no.ntnu.hikingstore_6.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {
    @Override
    Optional<Product> findById(Long aLong);

    @Override
    Iterable<Product> findAll();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(Product entity);
}
