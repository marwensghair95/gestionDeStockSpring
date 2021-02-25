package fivePoints.spring.GestionDeStock.repositories;

import fivePoints.spring.GestionDeStock.models.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Integer> {
}
