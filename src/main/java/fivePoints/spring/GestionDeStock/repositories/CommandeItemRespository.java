package fivePoints.spring.GestionDeStock.repositories;

import fivePoints.spring.GestionDeStock.models.CommandeItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommandeItemRespository extends JpaRepository<CommandeItem, Long> {
    // find item by id
    Optional<CommandeItem> findById(Long id);
}
