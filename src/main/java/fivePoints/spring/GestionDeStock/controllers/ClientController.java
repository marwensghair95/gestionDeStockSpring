package fivePoints.spring.GestionDeStock.controllers;


import fivePoints.spring.GestionDeStock.models.Client;
import fivePoints.spring.GestionDeStock.payload.responses.MessageResponse;
import fivePoints.spring.GestionDeStock.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins="http://localhost:4200/")
@RestController
@RequestMapping(value = "clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping("/addClient")
    public ResponseEntity<MessageResponse> addClient(@RequestBody Client client) {
        String message = clientService.addClient(client);
        return ResponseEntity.ok().body(new MessageResponse(message));
    }


    @GetMapping("/allClients")
    @ResponseBody
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> listUsers = this.clientService.getAllClients();
        return new ResponseEntity<>(listUsers, HttpStatus.OK);
    }

    @GetMapping("getClientById/{idClient}")
    public ResponseEntity<Client> getClientByID(@PathVariable(value="idClient") int idClient) {
        Client client = clientService.getClientByID(idClient);
        if(client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(client);
    }

    @PutMapping("updateClient/{id}")
    public  ResponseEntity<MessageResponse> updateClientByID(@PathVariable(value="id") int id, @RequestBody Client client) {
        String message =  clientService.updateClientByID(id, client);
       return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

    @DeleteMapping("deletClient/{idClient}")
    public ResponseEntity<MessageResponse> deletClient(@PathVariable(value="idClient") int idClient) {
        System.out.println(idClient);
        String message = clientService.deleteClientByID(idClient);
        return ResponseEntity.ok().body(new MessageResponse(message));
    }

    @PutMapping("updateFav/{id}")
    public  ResponseEntity<MessageResponse>updateFavByID(@PathVariable(value="id") int id) {
        String message =  clientService.updateFavByID(id);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }
    @GetMapping("/getFavoritClient")
    public ResponseEntity<List<Client>> getFavoritClient() {
        List<Client> listUsers = this.clientService.getFavoritClient();
        return new ResponseEntity<>(listUsers, HttpStatus.OK);
    }
}
