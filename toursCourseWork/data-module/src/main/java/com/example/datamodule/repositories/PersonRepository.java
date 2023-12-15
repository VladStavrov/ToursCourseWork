package com.example.datamodule.repositories;

import com.example.datamodule.models.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByPhoneNumber(String phoneNumber);
}
