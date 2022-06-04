package no.ntnu.hikingstore_6.repositories;

import no.ntnu.hikingstore_6.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Override
    void delete(User entity);
}
