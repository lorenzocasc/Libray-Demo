package com.example.demoservice.controller;

import com.example.demoservice.entity.Stock;
import com.example.demoservice.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public void set(@Valid @RequestBody Stock stock) {
        // assign parameters to taskDocumentEntity by constructor args or setters
        stockService.saveStock(stock);
    }

    //Save operation
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Stock saveStock(
            @Valid @RequestBody Stock stock) {
        return stockService.saveStock(stock);
    }


    //Count operation
    @RequestMapping(value = "count/{id:.+}", method = RequestMethod.GET)
    public Integer countBooks(@PathVariable("id")Integer id){
        return stockService.countBooks(id);
    }
    //Read operation
    @RequestMapping(value = "fetch", method = RequestMethod.GET)
    public List<Stock> fetchStockList() {
        return stockService.fetchStockList();
    }

    //Update operation
    @RequestMapping(value = "update/{stockid:.+}", method = RequestMethod.POST)
    public Stock updateStock(@RequestBody Stock stock, @PathVariable("stockid") Integer id) {
        return stockService.updateStock(stock, id);
    }

    //Delete operation
    @RequestMapping(value = "delete/{stockid:.+}", method = RequestMethod.POST)
    public String deleteStockById(@PathVariable("stockid") Integer id) {
        stockService.deleteStockById(id);
        return "Deleted Successfully";
    }


}
