package no.ntnu.hikingstore_6.repositories;

import no.ntnu.hikingstore_6.entities.ProductInOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInOrderRepository extends CrudRepository<ProductInOrder, Integer> {

}
