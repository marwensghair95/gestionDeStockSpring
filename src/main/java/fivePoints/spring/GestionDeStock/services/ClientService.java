package fivePoints.spring.GestionDeStock.services;

import fivePoints.spring.GestionDeStock.exceptions.ResourceNotFoundException;
import fivePoints.spring.GestionDeStock.models.Client;
import fivePoints.spring.GestionDeStock.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public String addClient(Client client) {
        clientRepository.save(client);
        return "Client added successfully";
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientByID(int id) {
        Optional<Client> userData =  clientRepository.findById(id);
        return userData.orElseThrow(() -> new ResourceNotFoundException("Client not found"));
    }


    public String updateClientByID(int id, Client client)
    {
        Optional<Client> userData = this.clientRepository.findById(id);
        if (userData.isPresent()) {
            Client existingClient = userData.orElse(null);
            existingClient.setEmailClient(client.getEmailClient());
            existingClient.setFaxClient(client.getFaxClient());
            existingClient.setAdresseClient(client.getAdresseClient());
            existingClient.setNameClient(client.getNameClient());
            existingClient.setRefClient(client.getRefClient());
            existingClient.setTelpClient(client.getTelpClient());
            existingClient.setModepaimentClient(client.getModepaimentClient());
            existingClient.setVilleClient(client.getVilleClient());
            existingClient.setTypeClient(client.getTypeClient());
            // save existingUser in the database
            this.clientRepository.save(existingClient);
            // return statement
            return "Client updated successfully!";
        } else {
            throw new ResourceNotFoundException("Client not found");
        }
    }


    public String updateFavByID(int id)
    {
        Optional<Client> userData = this.clientRepository.findById(id);
        if (userData.isPresent()) {
            Client existingClient = userData.orElse(null);
            existingClient.setFavorisClient(!existingClient.getFavorisClient());
            // save existingUser in the database
            this.clientRepository.save(existingClient);
            // return statement
            return "Client updated successfully!";
        } else {
            throw new ResourceNotFoundException("Client not found");
        }
    }
    public List<Client> getFavoritClient() {
        return  clientRepository.getByFavorisClient(true);
    }

    public String deleteClientByID(int id) {
        Optional<Client> existingClient = clientRepository.findById(id);
        if (existingClient.isPresent()) {
            return "Client deleted successfully!";
        } else {
            throw new ResourceNotFoundException("Client not found");
        }
    }
}
