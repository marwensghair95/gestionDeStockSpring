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
        double total=0;
       Commande com= commandeRepository.save(newCommande);

        for(CommandeItem p:commande.getCommandeItems()){
            CommandeItem commandeItem=new CommandeItem();
            commandeItem.setCommande(com);
            Optional<Produit> produit =  produitRepository.findById(p.getIdP());
            commandeItem.setProduit(produit.get());
            commandeItem.setPrice(produit.get().getPrixVente()*p.getQuantity());
           commandeItem.setQuantity(p.getQuantity());
            commandeItemRespository.save(commandeItem);
             total+=p.getQuantity()*produit.get().getPrixVente();
        }
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


    public String updateCommandeByID(int id, CommandeRequest commande)
    {
        Optional<Commande> commandeData = this.commandeRepository.findById(id);
        if (commandeData.isPresent()) {
            Commande newCommande = commandeData.orElse(null);
            Optional<Client> client =clientRepository.findById(commande.getIdClient());
            newCommande.setRefCommande(commande.getRefCommande());
            newCommande.setClient(client.get());
            newCommande.setDate_commande(commande.getDate_commande());
//            double total=0;
           for(CommandeItem p:newCommande.getCommandeItems()){

           CommandeItem ligneCommandes = commandeItemRespository.getOne(p.getId());


               System.out.println("commnd item"+ligneCommandes);
//                ligneCommande.setCommande(newCommande);
//                Optional<Produit> produit =  produitRepository.findById(p.getIdP());
//                ligneCommande.setProduit(produit.get());
//                ligneCommande.setPrice(produit.get().getPrixVente()*p.getQuantity());
//                ligneCommande.setQuantity(p.getQuantity());
//                this.commandeItemRespository.saveAndFlush(ligneCommande);
//                total+=p.getQuantity()*produit.get().getPrixVente();
            }
//            newCommande.setMontant_total(total);
            this.commandeRepository.saveAndFlush(newCommande);
            return "Commande added successfully";
        }
        else {
            throw new ResourceNotFoundException("Commande not found");
        }
    }

    public String deleteCommandeByID(int id) {
        Optional<Commande> existingCommande = commandeRepository.findById(id);
        if (existingCommande.isPresent()) {
            Commande commande = existingCommande.orElse(null);
            if (commande.getValide() == false) {
                for (CommandeItem p : commande.getCommandeItems()) {
                    Optional<CommandeItem> ligneCommandes = commandeItemRespository.findById(p.getId());
                    CommandeItem ligneCommande = ligneCommandes.orElse(null);
                    commandeRepository.delete(commande);
                    commandeItemRespository.delete(ligneCommande);

                }
                return "Commande deleted successfully!";
            }
                else {
                    return "Commande validé !";
                }
            } else {
                throw new ResourceNotFoundException("Commande not found");
            }
        }



    public String updateQte(int id)
    {
        Optional<Commande> existingCommande = commandeRepository.findById(id);
        if (existingCommande.isPresent()) {
            Commande commande = existingCommande.orElse(null);
            //verfier Qte stock
            for(CommandeItem p:commande.getCommandeItems()){
                Optional<Produit> produit =  produitRepository.findById(p.getProduit().getId());
                Produit existingProduit = produit.orElse(null);
                if (existingProduit.getQteProduit()<p.getQuantity()){
                    return "Quantite Produit '"+existingProduit.getNameProduit()+"' èpuise !";
                }
              }
        //update qte
        for (CommandeItem p : commande.getCommandeItems()) {
            Optional<Produit> produit = produitRepository.findById(p.getProduit().getId());
            Produit existingProduit = produit.orElse(null);
            existingProduit.setQteProduit(existingProduit.getQteProduit() - p.getQuantity());
            this.produitRepository.save(existingProduit);
        }
        //valide commande
            commande.setValide(true);
            this.commandeRepository.saveAndFlush(commande);
            return "commande updated successfully!";
        } else {
            throw new ResourceNotFoundException("commande not found");
        }
    }
}
