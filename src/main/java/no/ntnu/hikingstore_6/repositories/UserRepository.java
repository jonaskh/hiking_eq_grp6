package no.ntnu.hikingstore_6.repositories;

import java.util.Optional;
import no.ntnu.hikingstore_6.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {


    Optional<User> findByEmail(String email);

    @Query(value = "select cart_id from users where id = ?",nativeQuery = true)
    Long findCartID(Long id);
}


