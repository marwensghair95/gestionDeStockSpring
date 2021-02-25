package fivePoints.spring.GestionDeStock.repositories;

import fivePoints.spring.GestionDeStock.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    //   get client By fav
    public List<Client> getByFavorisClient(Boolean fav);
}
