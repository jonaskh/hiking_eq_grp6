package no.ntnu.hikingstore_6.repositories;

import no.ntnu.hikingstore_6.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findById(int id);

    Role findByName(String name);

}
