package fivePoints.spring.GestionDeStock.controllers;


import fivePoints.spring.GestionDeStock.models.Categorie;
import fivePoints.spring.GestionDeStock.payload.responses.MessageResponse;
import fivePoints.spring.GestionDeStock.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins="http://localhost:4200/")
@RestController
@RequestMapping(value = "/categorie")
public class CategorieController {

    @Autowired
    CategorieService categorieService;

    @PostMapping("/addCategorie")
    public ResponseEntity<MessageResponse> addCategorie(@RequestBody Categorie categorie) {
        String message = categorieService.addCategorie(categorie);
        return ResponseEntity.ok().body(new MessageResponse(message));
    }


    @GetMapping("/allCategories")
    @ResponseBody
    public ResponseEntity<List<Categorie>> getAllUsers() {
        List<Categorie> listUsers = this.categorieService.getAllCategorie();
        return new ResponseEntity<>(listUsers, HttpStatus.OK);
    }

    @GetMapping("getCategorieById/{id}")
    public ResponseEntity<Categorie> getCategorieByID(@PathVariable(value="id") int id) {
        Categorie user = categorieService.getCategorieByID(id);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("updateCategorie/{id}")
    public  ResponseEntity<MessageResponse> updateUserByID(@PathVariable(value="id") int id, @RequestBody Categorie user) {
        String message =  categorieService.updateCategorieByID(id, user);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

    @DeleteMapping("deletCategorie/{id}")
    public ResponseEntity<MessageResponse> deleteUserByID(@PathVariable(value="id") int user) {
        String message = categorieService.deleteCategorieByID(user);
        return ResponseEntity.ok().body(new MessageResponse(message));
    }
}
