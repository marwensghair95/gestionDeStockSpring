package fivePoints.spring.GestionDeStock.services;

import fivePoints.spring.GestionDeStock.exceptions.ResourceNotFoundException;
import fivePoints.spring.GestionDeStock.models.Produit;
import fivePoints.spring.GestionDeStock.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {
    @Autowired
    ProduitRepository produitRepository;

    public String addProduit(Produit produit) {
        produitRepository.save(produit);
        return "Produit added successfully";
    }

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    public Produit getProduitByID(int id) {
        Optional<Produit> userData =  produitRepository.findById(id);
        return userData.orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }


    public String updateProduitByID(int id, Produit produit)
    {
        Optional<Produit> produitData = this.produitRepository.findById(id);
        if (produitData.isPresent()) {
            Produit existingProduit = produitData.orElse(null);
            existingProduit.setNameProduit(produit.getNameProduit());

//             save existingUser in the database
            this.produitRepository.save(existingProduit);
            // return statement
            return "Produit updated successfully!";
        } else {
            throw new ResourceNotFoundException("Produit not found");
        }
    }

    public String deleteProduitByID(int id) {
        Optional<Produit> existingUser = produitRepository.findById(id);
        if (existingUser.isPresent()) {
            produitRepository.delete(existingUser.get());
            return "User deleted successfully!";
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }
}
