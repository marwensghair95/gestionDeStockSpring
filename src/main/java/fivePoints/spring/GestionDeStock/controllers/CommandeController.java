package fivePoints.spring.GestionDeStock.controllers;


import fivePoints.spring.GestionDeStock.models.Commande;
import fivePoints.spring.GestionDeStock.payload.requests.CommandeRequest;
import fivePoints.spring.GestionDeStock.payload.responses.MessageResponse;
import fivePoints.spring.GestionDeStock.services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins="http://localhost:4200/")
@RestController
@RequestMapping(value = "/commandes")
public class CommandeController {

    @Autowired
    CommandeService commandeService;

    @PostMapping("/addCommande")
    public ResponseEntity<MessageResponse> addCommande(@RequestBody CommandeRequest commande) {
        String message = commandeService.addCommande(commande);
        return ResponseEntity.ok().body(new MessageResponse(message));
    }


    @GetMapping("/allcommande")
    @PreAuthorize(" hasRole('USER') ")
    public ResponseEntity<List<Commande>> getAllUsers() {
        List<Commande> listCommande = this.commandeService.getAllCommandes();
        return new ResponseEntity<>(listCommande, HttpStatus.OK);
    }

    @GetMapping("getCommandeById/{id}")
    public ResponseEntity<Commande> getCommandeByID(@PathVariable(value="id") int id) {
        Commande user = commandeService.getCommandeByID(id);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("updateCommande/{id}")
    public  ResponseEntity<MessageResponse> updateUserByID(@PathVariable(value="id") int id, @RequestBody Commande commande) {
        String message =  commandeService.updateCommandeByID(id, commande);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

    @DeleteMapping("deletCommande/{id}")
    public ResponseEntity<MessageResponse> deleteCommandeByID(@PathVariable(value="id") int command) {
        String message = commandeService.deleteCommandeByID(command);
        return ResponseEntity.ok().body(new MessageResponse(message));

    }
}
