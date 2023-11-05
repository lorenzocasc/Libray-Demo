package com.example.demoservice.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="stockid")
    private Integer stockid;

    @Column(name = "REPARTO")
    private String reparto;

    @Column(name = "QUANTITA")
    private Integer quantita;

    @Column(name = "SCAFFALE")
    private Integer scaffale;

    @Column(name = "POS")
    private Integer position;


    @ManyToOne
    @JoinColumn(name = "book_id",referencedColumnName = "book_id")
    private Book book;

    public void decreaseQuantity(){
        quantita--;
    }


}
