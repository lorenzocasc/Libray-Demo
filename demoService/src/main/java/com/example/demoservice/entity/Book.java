package com.example.demoservice.entity;
import com.example.demoservice.entity.Reservation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer id;
    @NotNull
    private String bookName;
    private String author;
    private Date date = new Date(System.currentTimeMillis());
    private Integer pagesNumber;


    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private Set<Stock> stocks = new HashSet<>();


    @OneToOne(mappedBy = "book")
    @JsonIgnore
    private Reservation reservation;

    public boolean stocksCheck(){
        if(stocks.isEmpty())return false;
        return true;
    }
}
