package no.ntnu.hikingstore_6.repositories;

import no.ntnu.hikingstore_6.entities.Role;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    @Override
    Optional<Role> findById(Long aLong);

    @Query("SELECT r FROM Role r WHERE r.name = ?1")
    public Role findByName(String name);

}
