package fivePoints.spring.GestionDeStock.controllers;

import fivePoints.spring.GestionDeStock.models.User;
import fivePoints.spring.GestionDeStock.payload.requests.RegisterRequest;
import fivePoints.spring.GestionDeStock.payload.responses.MessageResponse;
import fivePoints.spring.GestionDeStock.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
//@ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<MessageResponse> addUser(@RequestBody RegisterRequest registerRequest) {
        String message = userService.addUser(registerRequest);
        return ResponseEntity.ok().body(new MessageResponse(message));
    }


    @GetMapping("/allUsers")
    @ResponseBody
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> listUsers = this.userService.getAllUsers();
        return new ResponseEntity<>(listUsers, HttpStatus.OK);
    }

    @GetMapping("getUserById/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable(value="id") long id) {
        User user = userService.getUserByID(id);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("updateUser/{id}")
    public  ResponseEntity<MessageResponse> updateUserByID(@PathVariable(value="id") long id, @RequestBody User user) {
        String message =  userService.updateUserByID(id, user);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

    @DeleteMapping("deletUser/{id}")
    public ResponseEntity<MessageResponse> deleteUserByID(@PathVariable(value="id") long id) {
        String message = userService.deleteUserByID(id);
        return ResponseEntity.ok().body(new MessageResponse(message));
    }
    // Affecter Role to user
    @PutMapping("/affect-role/{idUser}/{idRole}")
    public ResponseEntity<MessageResponse> affectUserToRole(long idUser, long idRole) {
        String message = this.userService.affectUserToRole(idUser, idRole);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }
}
