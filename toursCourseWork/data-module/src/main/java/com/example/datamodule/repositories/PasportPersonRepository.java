package com.example.datamodule.repositories;

import com.example.datamodule.models.person.PasportPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasportPersonRepository extends JpaRepository<PasportPerson,Long> {
}
