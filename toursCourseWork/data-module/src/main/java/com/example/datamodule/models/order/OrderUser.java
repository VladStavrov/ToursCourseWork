/*
package com.example.datamodule.models.order;

import com.example.datamodule.models.tour.Tour;
import com.example.datamodule.models.person.PasportPerson;
import com.example.datamodule.models.person.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateOfCreated;
    @PrePersist
    private void init(){
        dateOfCreated=LocalDateTime.now();
    }
    private String status;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paycard_id", referencedColumnName = "id")
    private PayCard payCard;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orderUser")
    private List<PasportPerson> personList= new ArrayList<>();

}
*/
