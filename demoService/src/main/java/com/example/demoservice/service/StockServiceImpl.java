package com.example.demoservice.service;

import com.example.demoservice.entity.Stock;
import com.example.demoservice.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Iterator;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {


    private final StockRepository stockRepository;


    //Save operation
    @Override
    public Stock saveStock(Stock stock) {
        return stockRepository.save(stock);
    }

    //Read operation
    @Override
    public List<Stock> fetchStockList() {
        return (List<Stock>) stockRepository.findAll();
    }

    //Count Operation
    @Override
    public Integer countBooks(Integer id) {
        Integer count = 0;
        Set<Stock> stocksSet = stockRepository.findAllByBook_id(id);
        Iterator<Stock> stockIterator = stocksSet.iterator();
        while (stockIterator.hasNext()) {
            count = count + stockIterator.next().getQuantita();
        }
        return count;
    }

    @Override
    public void bookBorrowed(Integer id) {
        Set<Stock> stocksSet = stockRepository.findAllByBook_id(id);
        Iterator<Stock> stockIterator = stocksSet.iterator();
        while (stockIterator.hasNext()) {
            Stock st = stockIterator.next();
            if(st.getQuantita()>0){
                st.decreaseQuantity();
                break;
            }
        }
    }



    //Update operation
    @Override
    public Stock
    updateStock(Stock stock,
                Integer id) {
        Stock stockDB
                = stockRepository.findById(id)
                .get();

        if (Objects.nonNull(stock.getQuantita())) {
            stockDB.setQuantita(stock.getQuantita());
        }


        if (Objects.nonNull(stock.getReparto())
                && !"".equalsIgnoreCase(
                stock.getReparto())) {
            stockDB.setReparto(
                    stock.getReparto());
        }

        if (Objects.nonNull(
                stock.getScaffale())
                && !"".equalsIgnoreCase(
                stock.getScaffale().toString())) {
            stockDB.setScaffale(
                    stock.getScaffale());
        }

        if (Objects.nonNull(stock.getScaffale())
                && !"".equalsIgnoreCase(
                stock.getScaffale().toString())) {
            stockDB.setScaffale(
                    stock.getScaffale());
        }

        return stockRepository.save(stockDB);
    }


    //Delete operation
    @Override
    public void deleteStockById(Integer id) {
        stockRepository.deleteById(id);
    }


}
