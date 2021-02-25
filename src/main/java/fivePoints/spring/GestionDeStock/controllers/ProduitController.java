package fivePoints.spring.GestionDeStock.controllers;

import fivePoints.spring.GestionDeStock.models.Produit;
import fivePoints.spring.GestionDeStock.payload.responses.MessageResponse;
import fivePoints.spring.GestionDeStock.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@CrossOrigin(origins="*")
@RestController
@RequestMapping(value = "/produits")
public class ProduitController {

    @Autowired
    ProduitService produitService;

    @PostMapping("/addProduit")
    public ResponseEntity<MessageResponse> addProduit(@RequestBody Produit produit) {
        String message = produitService.addProduit(produit);
        return ResponseEntity.ok().body(new MessageResponse(message));
    }


    @GetMapping("/allProduits")
    @ResponseBody
    public ResponseEntity<List<Produit>> getAllUsers() {
        List<Produit> listUsers = this.produitService.getAllProduits();
        return new ResponseEntity<>(listUsers, HttpStatus.OK);
    }

    @GetMapping("getProduitById/{id}")
    public ResponseEntity<Produit> getUserByID(@PathVariable(value="id") int id) {
        Produit produit = produitService.getProduitByID(id);
        if(produit == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(produit);
    }

    @PutMapping("updateProduit/{id}")
    public  ResponseEntity<MessageResponse> updateUserByID(@PathVariable(value="id") int id, @RequestBody Produit produit) {
        String message =  produitService.updateProduitByID(id, produit);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

    @DeleteMapping("deletProduit/{id}")
    public ResponseEntity<MessageResponse> deleteProduitByID(@PathVariable(value="id") int id) {
        String message = produitService.deleteProduitByID(id);
        return ResponseEntity.ok().body(new MessageResponse(message));
    }
}
