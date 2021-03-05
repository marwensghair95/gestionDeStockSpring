package fivePoints.spring.GestionDeStock.services;

import fivePoints.spring.GestionDeStock.exceptions.ResourceNotFoundException;
import fivePoints.spring.GestionDeStock.models.*;
import fivePoints.spring.GestionDeStock.payload.requests.CommandeRequest;
import fivePoints.spring.GestionDeStock.repositories.ClientRepository;
import fivePoints.spring.GestionDeStock.repositories.CommandeItemRespository;
import fivePoints.spring.GestionDeStock.repositories.CommandeRepository;
import fivePoints.spring.GestionDeStock.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {
    @Autowired
    CommandeRepository commandeRepository;

    @Autowired
    ProduitRepository produitRepository;

    @Autowired
    CommandeItemRespository commandeItemRespository;

    @Autowired
    ClientRepository clientRepository;

    public String addCommande(CommandeRequest commande) {

        Optional<Client> client =clientRepository.findById(commande.getIdClient());

        Commande newCommande =new Commande();
        newCommande.setRefCommande(commande.getRefCommande());
        newCommande.setClient(client.get());
        newCommande.setDate_commande(commande.getDate_commande());
        newCommande.setValide(commande.getValide());
//        newCommande.setMontant_total(commande.getMontant_total());
        double total=0;
       Commande com= commandeRepository.save(newCommande);

        for(CommandeItem p:commande.getCommandeItems()){
            CommandeItem commandeItem=new CommandeItem();
            commandeItem.setCommande(com);
            Optional<Produit> produit =  produitRepository.findById(p.getIdP());
//            System.out.println(produit);
            commandeItem.setProduit(produit.get());
            commandeItem.setPrice(produit.get().getPrixVente()*p.getQuantity());
           commandeItem.setQuantity(p.getQuantity());
            commandeItemRespository.save(commandeItem);
             total+=p.getQuantity()*produit.get().getPrixVente();
        }
//        System.out.println(total);
       com.setMontant_total(total);
        commandeRepository.saveAndFlush(com);
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
        Optional<Commande> existingCommande = commandeRepository.findById(id);
        if (existingCommande.isPresent()) {

            Commande commande = existingCommande.orElse(null);
            for (CommandeItem p: commande.getCommandeItems()) {
                Optional<CommandeItem> ligneCommande = commandeItemRespository.findById(p.getId());
                 commandeRepository.delete(commande);
                 commandeItemRespository.delete(ligneCommande.get());

            }
            return "Commande deleted successfully!";
            }else {
            throw new ResourceNotFoundException("Commande not found");
        }

    }

    public String updateQte(int id)
    {
        Optional<Commande> existingCommande = commandeRepository.findById(id);

        if (existingCommande.isPresent()) {
            Commande commande = existingCommande.orElse(null);
            for(CommandeItem p:commande.getCommandeItems()){
                Optional<Produit> produit =  produitRepository.findById(p.getProduit().getId());
                Produit existingProduit = produit.orElse(null);
              if (existingProduit.getQteProduit()<p.getQuantity()){
                  return "Quantite Produit '"+existingProduit.getNameProduit()+"' èpuise !";
              } else{
                  existingProduit.setQteProduit(existingProduit.getQteProduit()-p.getQuantity());
                  this.produitRepository.save(existingProduit);
              }


   }
            commande.setValide(true);
            this.commandeRepository.saveAndFlush(commande);
            return "commande updated successfully!";
        } else {
            throw new ResourceNotFoundException("commande not found");
        }
    }
}
