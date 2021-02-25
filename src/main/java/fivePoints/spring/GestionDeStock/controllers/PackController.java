package fivePoints.spring.GestionDeStock.controllers;


import fivePoints.spring.GestionDeStock.models.Pack;
import fivePoints.spring.GestionDeStock.payload.responses.MessageResponse;

import fivePoints.spring.GestionDeStock.services.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins="http://localhost:4200/")
@RestController
@RequestMapping(value = "/users")
public class PackController {

    @Autowired
    PackService packService;

    @PostMapping("/addPack")
    public ResponseEntity<MessageResponse> addPack(@RequestBody Pack pack) {
        String message = packService.addPack(pack);
        return ResponseEntity.ok().body(new MessageResponse(message));
    }


    @GetMapping("/allPacks")
    @ResponseBody
    public ResponseEntity<List<Pack>> getAllPacks() {
        List<Pack> listUsers = this.packService.getAllPacks();
        return new ResponseEntity<>(listUsers, HttpStatus.OK);
    }

    @GetMapping("getPackById/{id}")
    public ResponseEntity<Pack> getUserByID(@PathVariable(value="id") int id) {
        Pack user = packService.getPackByID(id);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("updatePack/{id}")
    public  ResponseEntity<MessageResponse> updatePackByID(@PathVariable(value="id") int id, @RequestBody Pack pack) {
        String message =  packService.updatePackByID(id, pack);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

    @DeleteMapping("deletPack/{id}")
    public ResponseEntity<MessageResponse> deletePackByID(@PathVariable(value="id") int id) {
        String message = packService.deletePackByID(id);
        return ResponseEntity.ok().body(new MessageResponse(message));
    }
}
