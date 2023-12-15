package com.example.datamodule.models.order;

import com.example.datamodule.models.tour.Tour;
import com.example.datamodule.models.person.PasportPerson;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCustom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phoneNumber;
    private String name;
    private String lastname;
    private String status;
    private String dateOfCreated;
    @PrePersist
    private void init(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        dateOfCreated=LocalDateTime.now().format(formatter);

    }

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paycard_id", referencedColumnName = "id")
    private PayCard payCard;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orderCustom")
    private List<PasportPerson> personList= new ArrayList<>();


}
