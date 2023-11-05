package com.example.demoservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_id")
    private Integer id;

    @NotNull
    private String username;

    @NotNull
    private String birthDate;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Reservation> reservationSet = new HashSet<>();


    public Integer countSet(){return reservationSet.size();}
}
