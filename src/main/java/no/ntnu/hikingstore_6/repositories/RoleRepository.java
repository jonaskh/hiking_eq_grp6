package no.ntnu.hikingstore_6.repositories;

import no.ntnu.hikingstore_6.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}