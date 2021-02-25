package fivePoints.spring.GestionDeStock.repositories;

import fivePoints.spring.GestionDeStock.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
