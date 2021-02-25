package fivePoints.spring.GestionDeStock.services;

import fivePoints.spring.GestionDeStock.exceptions.ResourceNotFoundException;
import fivePoints.spring.GestionDeStock.models.Commande;
import fivePoints.spring.GestionDeStock.repositories.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {
    @Autowired
    CommandeRepository commandeRepository;

    public String addCommande(Commande commande) {
        commandeRepository.save(commande);
        return "Commande added successfully";
    }

    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    public Commande getCommandeByID(int id) {
        Optional<Commande> userData =  commandeRepository.findById(id);
        return userData.orElseThrow(() -> new ResourceNotFoundException("Commande not found"));
    }


    public String updateCommandeByID(int id, Commande commande)
    {
        Optional<Commande> CommandeData = this.commandeRepository.findById(id);
        if (CommandeData.isPresent()) {
            Commande existingUser = CommandeData.orElse(null);
//            existingUser.setFirstName(client.getFirstName());
//            existingUser.setLastName(user.getLastName());
//            existingUser.setEmail(user.getEmail());
//            existingUser.setPassword(user.getPassword());
            // save existingUser in the database
            this.commandeRepository.save(existingUser);
            // return statement
            return "Commande updated successfully!";
        } else {
            throw new ResourceNotFoundException("Commande not found");
        }
    }

    public String deleteCommandeByID(int id) {
        Optional<Commande> existingUser = commandeRepository.findById(id);
        if (existingUser.isPresent()) {
            commandeRepository.delete(existingUser.get());
            return "Commande deleted successfully!";
        } else {
            throw new ResourceNotFoundException("Commande not found");
        }
    }
}
