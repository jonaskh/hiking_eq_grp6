package no.ntnu.hikingstore_6.repositories;

import no.ntnu.hikingstore_6.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Override
    Optional<Role> findById(Integer integer);
}