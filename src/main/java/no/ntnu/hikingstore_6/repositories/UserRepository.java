package no.ntnu.hikingstore_6.repositories;

import java.util.Optional;
import no.ntnu.hikingstore_6.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);
}


