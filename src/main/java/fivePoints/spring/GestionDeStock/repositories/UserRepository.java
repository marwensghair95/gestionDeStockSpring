package fivePoints.spring.GestionDeStock.repositories;

import fivePoints.spring.GestionDeStock.models.Client;
import fivePoints.spring.GestionDeStock.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
