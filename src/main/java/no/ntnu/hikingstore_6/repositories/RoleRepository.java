package no.ntnu.xxs.user;

import no.ntnu.hikingstore_6.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}