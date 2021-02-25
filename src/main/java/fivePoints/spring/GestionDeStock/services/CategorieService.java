package fivePoints.spring.GestionDeStock.services;

import fivePoints.spring.GestionDeStock.exceptions.ResourceNotFoundException;
import fivePoints.spring.GestionDeStock.models.Categorie;
import fivePoints.spring.GestionDeStock.repositories.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {
    @Autowired
    CategorieRepository categorieRepository;

    public String addCategorie(Categorie categorie) {
        categorieRepository.save(categorie);
        return "User added successfully";
    }

    public List<Categorie> getAllCategorie() {
        return categorieRepository.findAll();
    }

    public Categorie getCategorieByID(int id) {
        Optional<Categorie> userData =  categorieRepository.findById(id);
        return userData.orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }


    public String updateCategorieByID(int id, Categorie categorie)
    {
        Optional<Categorie> categorieData = this.categorieRepository.findById(id);
        if (categorieData.isPresent()) {
            Categorie existingCat = categorieData.orElse(null);
//            existingUser.setFirstName(user.getFirstName());
//            existingUser.setLastName(user.getLastName());
//            existingUser.setEmail(user.getEmail());
//            existingUser.setPassword(user.getPassword());
            // save existingUser in the database
            this.categorieRepository.save(existingCat);
            // return statement
            return "Categorie updated successfully!";
        } else {
            throw new ResourceNotFoundException("Categorie not found");
        }
    }

    public String deleteCategorieByID(int id) {
        Optional<Categorie> existingCategorie = categorieRepository.findById(id);
        if (existingCategorie.isPresent()) {
            categorieRepository.delete(existingCategorie.get());
            return "User deleted successfully!";
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }
}
