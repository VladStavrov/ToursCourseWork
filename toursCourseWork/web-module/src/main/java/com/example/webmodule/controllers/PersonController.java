package com.example.webmodule.controllers;
import com.example.datamodule.dto.person.PersonInfoDTO;
import com.example.servicemodule.s.services.person.PersonService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping("/person/info")
    public ResponseEntity<?> getPersonInfo(Authentication authentication){
        return ResponseEntity.ok(personService.getPersonInfo(authentication.getName()));
    }
    @PutMapping("/person/update")
    public ResponseEntity<?> updatePersonInfo(Authentication authentication,
                                              @RequestBody PersonInfoDTO personInfoDTO){
        personService.updatePersonInfo(personInfoDTO,authentication.getName());
        return ResponseEntity.ok("Все ок");
    }
}