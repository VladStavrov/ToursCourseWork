package com.example.servicemodule.s.services;

import com.example.datamodule.models.person.PasportPerson;
import com.example.datamodule.repositories.PasportPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParpostPersonService {
    private final PasportPersonRepository personRepository;
    public PasportPerson saveNewTourist(PasportPerson person){
       return personRepository.save(person);

    }
}
