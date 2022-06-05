package no.ntnu.hikingstore_6.repositories;

import no.ntnu.hikingstore_6.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    @Override
    List<Product> findAll();



}
