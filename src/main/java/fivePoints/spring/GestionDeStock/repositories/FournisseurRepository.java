package fivePoints.spring.GestionDeStock.repositories;

import fivePoints.spring.GestionDeStock.models.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer> {
    //   get client By fav
    public List<Fournisseur> getByFavorisFournisseur(Boolean fav);
}
