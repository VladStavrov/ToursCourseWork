package com.example.datamodule.models.person;

import com.example.datamodule.models.order.OrderCustom;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "person")
@EqualsAndHashCode
public class Person /*implements UserDetails*/ {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String phoneNumber;
    private String password;
    private String name;
    private String lastname;
    private LocalDateTime dateOfCreated;
    @PrePersist
    private void init(){
        dateOfCreated=LocalDateTime.now();
    }
    /*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
    private List<OrderUser> orderUserList= new ArrayList<>();
*/

    @ManyToMany
    @JoinTable(
            name = "users_orders",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<OrderCustom> orders;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles;


}