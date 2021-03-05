package fivePoints.spring.GestionDeStock.repositories;

import fivePoints.spring.GestionDeStock.models.CommandeItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeItemRespository extends JpaRepository<CommandeItem, Long> {
}
