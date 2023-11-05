package com.example.demoservice.repository;

import com.example.demoservice.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface StockRepository extends JpaRepository<Stock, Integer> {

    @Query(value = "SELECT sum(quantita) from Stock where book_id = ?1", nativeQuery = true)
    Integer count(Integer id);

    Set<Stock> findAllByBook_id(Integer id);

}
