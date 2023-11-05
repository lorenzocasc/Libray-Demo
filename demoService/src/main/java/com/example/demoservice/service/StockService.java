package com.example.demoservice.service;
import com.example.demoservice.entity.Stock;
import java.util.List;

public interface StockService {
    Stock saveStock(Stock stock);

    // Read operation
    List<Stock> fetchStockList();

    // Count operation
    Integer countBooks(Integer id);

    // Book borrowed
    void bookBorrowed(Integer id);

    //Update operation
    Stock updateStock(Stock Stock, Integer id);

    //Delete operation
    void deleteStockById(Integer id);
}
