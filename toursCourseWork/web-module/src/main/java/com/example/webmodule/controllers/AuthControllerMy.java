package com.example.webmodule.controllers;


import com.example.datamodule.dto.person.RegistrationUserDTO;
import com.example.datamodule.repositories.PersonRepository;
import com.example.datamodule.request.JwtRequest;


import com.example.servicemodule.s.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthControllerMy {
    private  final PersonRepository personRepository;

    private final AuthService authService;


    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
       return authService.createAuthToken(authRequest);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewPerson(@RequestBody RegistrationUserDTO registrationUserDTO)  {
       return ResponseEntity.ok(authService.createNewPerson(registrationUserDTO));
    }

}