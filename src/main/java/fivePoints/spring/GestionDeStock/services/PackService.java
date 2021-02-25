package fivePoints.spring.GestionDeStock.services;

import fivePoints.spring.GestionDeStock.exceptions.ResourceNotFoundException;
import fivePoints.spring.GestionDeStock.models.Pack;
import fivePoints.spring.GestionDeStock.repositories.PackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackService {
    @Autowired
    PackRepository packRepository;

    public String addPack(Pack pack) {
        packRepository.save(pack);
        return "Pack added successfully";
    }

    public List<Pack> getAllPacks() {
        return packRepository.findAll();
    }

    public Pack getPackByID(int id) {
        Optional<Pack> userData =  packRepository.findById(id);
        return userData.orElseThrow(() -> new ResourceNotFoundException("Pack not found"));
    }


    public String updatePackByID(int id, Pack pack)
    {
        Optional<Pack> packData = this.packRepository.findById(id);
        if (packData.isPresent()) {
            Pack existingPack = packData.orElse(null);
//            existingUser.setFirstName(user.getFirstName());
//            existingUser.setLastName(user.getLastName());
//            existingUser.setEmail(user.getEmail());
//            existingUser.setPassword(user.getPassword());
            // save existingUser in the database
            this.packRepository.save(existingPack);
            // return statement
            return "User updated successfully!";
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    public String deletePackByID(int id) {
        Optional<Pack> existingPack = packRepository.findById(id);
        if (existingPack.isPresent()) {
            packRepository.delete(existingPack.get());
            return "Pack deleted successfully!";
        } else {
            throw new ResourceNotFoundException("Pack not found");
        }
    }
}
