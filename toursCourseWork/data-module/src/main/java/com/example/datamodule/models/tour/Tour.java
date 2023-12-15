package com.example.datamodule.models.tour;

import com.example.datamodule.models.order.OrderCustom;
import com.example.datamodule.models.tour.hotel.Hotel;
import com.example.datamodule.models.tour.info.TourOption;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "touroption_id", referencedColumnName = "id")
    private TourOption tourOption;

   /* @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tour")
    private List<OrderUser> orderUserList= new ArrayList<>();
*/
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tour")
    private List<OrderCustom> orderList= new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
