package no.ntnu.hikingstore_6.repositories;

import no.ntnu.hikingstore_6.entities.CartItem;

import no.ntnu.hikingstore_6.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Integer> {

    public List<CartItem> findByUser(User user);
}
