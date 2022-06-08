package no.ntnu.hikingstore_6.repositories;

import no.ntnu.hikingstore_6.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findById(int id);

    Role findByName(String name);

}
