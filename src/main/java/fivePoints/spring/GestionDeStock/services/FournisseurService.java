package fivePoints.spring.GestionDeStock.services;

import fivePoints.spring.GestionDeStock.exceptions.ResourceNotFoundException;
import fivePoints.spring.GestionDeStock.models.Client;
import fivePoints.spring.GestionDeStock.models.Fournisseur;
import fivePoints.spring.GestionDeStock.repositories.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FournisseurService {
    @Autowired
    FournisseurRepository fournisseurRepository;

    public String addFournisseur(Fournisseur fournisseur) {
        fournisseurRepository.save(fournisseur);
        return "Fournisseur added successfully";
    }

    public List<Fournisseur> getAllFournisseurs() {
        return fournisseurRepository.findAll();
    }

    public Fournisseur getFournisseurByID(int id) {
        Optional<Fournisseur> userData =  fournisseurRepository.findById(id);
        return userData.orElseThrow(() -> new ResourceNotFoundException("Fournisseur not found"));
    }


    public String updateFournisseurByID(int id, Fournisseur fournisseur)
    {
        Optional<Fournisseur> produitData = this.fournisseurRepository.findById(id);
        if (produitData.isPresent()) {
            Fournisseur existingProduit = produitData.orElse(null);

            existingProduit=  this.fournisseurRepository.saveAndFlush(fournisseur);

            return "Produit updated successfully!";
        } else {
            throw new ResourceNotFoundException("Produit not found");
        }
    }

    public String deleteFournisseurByID(int id) {
        Optional<Fournisseur> existingFournisseur = fournisseurRepository.findById(id);
        System.out.println(id);
        if (existingFournisseur.isPresent()) {
            fournisseurRepository.delete(existingFournisseur.get());
            return "Fournisseur deleted successfully!";
        } else {
            throw new ResourceNotFoundException("Fournisseur not found");
        }
    }
    public String updateFavByID(int id)
    {
        Optional<Fournisseur> userData = this.fournisseurRepository.findById(id);
        if (userData.isPresent()) {
            Fournisseur existingClient = userData.orElse(null);
            existingClient.setFavorisFournisseur(!existingClient.getFavorisFournisseur());
            // save existingUser in the database
            this.fournisseurRepository.save(existingClient);
            // return statement
            return "Fournisseur updated successfully!";
        } else {
            throw new ResourceNotFoundException("Fournisseur not found");
        }
    }
    public List<Fournisseur> getFavoritFournisseur() {
        return  fournisseurRepository.getByFavorisFournisseur(true);
    }

}
