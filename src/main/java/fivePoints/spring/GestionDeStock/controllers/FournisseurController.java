package fivePoints.spring.GestionDeStock.controllers;


import fivePoints.spring.GestionDeStock.models.Client;
import fivePoints.spring.GestionDeStock.models.Fournisseur;
import fivePoints.spring.GestionDeStock.payload.responses.MessageResponse;
import fivePoints.spring.GestionDeStock.services.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins="http://localhost:4200/")
@RestController
@RequestMapping(value = "fournisseurs")
public class FournisseurController {

    @Autowired
    FournisseurService fournisseurService;

    @PostMapping("/addFournisseur")
    public ResponseEntity<MessageResponse> addUser(@RequestBody Fournisseur fournisseur) {
        String message = fournisseurService.addFournisseur(fournisseur);
        return ResponseEntity.ok().body(new MessageResponse(message));
    }


    @GetMapping("/allFournisseur")
    @ResponseBody
    public ResponseEntity<List<Fournisseur>> getAllUsers() {
        List<Fournisseur> listUsers = this.fournisseurService.getAllFournisseurs();
        return new ResponseEntity<>(listUsers, HttpStatus.OK);
    }

    @GetMapping("getFournisseurById/{id}")
    public ResponseEntity<Fournisseur> getUserByID(@PathVariable(value="id") int id) {
        Fournisseur fournisseur = fournisseurService.getFournisseurByID(id);
        if(fournisseur == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(fournisseur);
    }

    @PutMapping("updateFournisseur/{id}")
    public  ResponseEntity<MessageResponse> updateUserByID(@PathVariable(value="id") int id, @RequestBody Fournisseur fournisseur) {
        String message =  fournisseurService.updateFournisseurByID(id, fournisseur);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

    @DeleteMapping("deletFournisseur/{id}")
    public ResponseEntity<MessageResponse> deleteFournisseurByID(@PathVariable(value="id") int id) {
        String message = fournisseurService.deleteFournisseurByID(id);
        return ResponseEntity.ok().body(new MessageResponse(message));
    }
    @PutMapping("updateFav/{id}")
    public  ResponseEntity<MessageResponse>updateFavByID(@PathVariable(value="id") int id) {
        String message =  fournisseurService.updateFavByID(id);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }
    @GetMapping("/getFavoritFournisseur")
    public ResponseEntity<List<Fournisseur>> getFavoritFournisseur() {
        List<Fournisseur> listFournisseurs = this.fournisseurService.getFavoritFournisseur();
        return new ResponseEntity<>(listFournisseurs, HttpStatus.OK);
    }
}
