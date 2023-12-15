package com.example.datamodule.models.person;


import com.example.datamodule.models.order.OrderCustom;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PasportPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sex;
    private String name;
    private String lastName;
    private String surname;
    private String birthDate;
    private String startDate;
    private String endDate;
    private String passportSeries;
    private String passportNumber;

   /* @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "orderuser_id")
    private OrderUser orderUser;
*/
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "ordercustom_id")
    private OrderCustom orderCustom;
}