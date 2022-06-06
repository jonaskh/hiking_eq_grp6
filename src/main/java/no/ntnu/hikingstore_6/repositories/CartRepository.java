package no.ntnu.hikingstore_6.repositories;

import no.ntnu.hikingstore_6.entities.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

    Optional<Cart> findById(Long id);
}
