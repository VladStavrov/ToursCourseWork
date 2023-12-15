package com.example.datamodule.models.tour.info;

import com.example.datamodule.models.tour.Tour;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dateStart;
    private String dateEnd;
    private int nights;
    private int total;
    private String typeRoom;
    private int peoples;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "food_id")
    private Food food;


    @OneToOne(mappedBy = "tourOption")
    private Tour tour;


}
