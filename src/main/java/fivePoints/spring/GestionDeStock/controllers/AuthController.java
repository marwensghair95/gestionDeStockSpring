package fivePoints.spring.GestionDeStock.controllers;

import fivePoints.spring.GestionDeStock.exceptions.EmailAlreadyUsedException;
import fivePoints.spring.GestionDeStock.payload.requests.LoginRequest;
import fivePoints.spring.GestionDeStock.payload.requests.RegisterRequest;
import fivePoints.spring.GestionDeStock.payload.responses.LoginResponse;
import fivePoints.spring.GestionDeStock.payload.responses.MessageResponse;
import fivePoints.spring.GestionDeStock.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest)
    {
        String token = this.authService.login(loginRequest);
        return ResponseEntity.ok(new LoginResponse(token,"Bearer", "Login successfully"));
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@Valid @RequestBody RegisterRequest registerRequest) throws EmailAlreadyUsedException {
        String message = this.authService.register(registerRequest);
        return ResponseEntity.ok(new MessageResponse(message));
    }
}
