package com.example.servicemodule.s.services;


import com.example.commonmodule.exception.AppError;
import com.example.commonmodule.exception.LocalException;
import com.example.datamodule.dto.person.PersonDTO;
import com.example.datamodule.dto.person.RegistrationUserDTO;
import com.example.datamodule.models.person.Person;
import com.example.datamodule.models.person.Role;
import com.example.datamodule.request.JwtRequest;
import com.example.datamodule.response.JwtResponse;
import com.example.commonmodule.utils.JWTUtil;
import com.example.servicemodule.s.services.person.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PersonService personService;
    private final JWTUtil jwtUtil;
    private  final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final Map<String, String> refreshStorage = new HashMap<>();

    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getPhoneNumber(),
                    authRequest.getPassword()));
        } catch(BadCredentialsException e){
            return new ResponseEntity<>( new AppError(HttpStatus.UNAUTHORIZED.value(),"Неправильный логин или пароль"),HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails;
        List<String> roles = new ArrayList<>();
        try {
            userDetails = personService.loadUserByUsername(authRequest.getPhoneNumber());
            Person person = personService.findByPhoneNumber(authRequest.getPhoneNumber());
            roles=person.getRoles().stream().map(Role::getName).collect(Collectors.toList());
        }
        catch(UsernameNotFoundException e){
            return new ResponseEntity<>( new AppError(HttpStatus.UNAUTHORIZED.value(),"Неправильный логин или пароль"),HttpStatus.UNAUTHORIZED);
        }
        String token = jwtUtil.generateToken(userDetails);


        System.out.println("token:" + token);
        System.out.println(roles);

        return ResponseEntity.ok(new JwtResponse(token,roles));
    }

    public ResponseEntity<PersonDTO> createNewPerson(@RequestBody RegistrationUserDTO registrationUserDTO)  {
        if(!registrationUserDTO.getPassword().equals(registrationUserDTO.getConfirmPassword())){
            throw new LocalException(HttpStatus.BAD_REQUEST,"ПАроли не совпадают");
           /* throw  new AppError(HttpStatus.BAD_REQUEST.value(), "ароли не совпадают");*/
            /*return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пароли не совпадают"),HttpStatus.BAD_REQUEST);
       */ }
        try{
            personService.findByPhoneNumber(registrationUserDTO.getPhoneNumber());
            throw new LocalException(HttpStatus.BAD_REQUEST,"Такой номер  уже зарегистрировано");
        }
        catch (UsernameNotFoundException e){
            Person person = personService.createPerson(registrationUserDTO);
            return ResponseEntity.ok(new PersonDTO(person.getId(), person.getPhoneNumber()));
        }

    }



}
