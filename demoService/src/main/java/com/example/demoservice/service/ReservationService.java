package com.example.demoservice.service;

import com.example.demoservice.entity.Reservation;
import com.example.demoservice.entity.Stock;


import com.example.demoservice.repository.ReservationRepository;
import com.example.demoservice.repository.StockRepository;
import com.example.demoservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ReservationService extends Exception {


    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    private final UserService userService;
    private final BookServiceImpl bookService;
    private final StockServiceImpl stockService;

    private final StockRepository stockRepository;
    //Save operation

    public Reservation saveReservation(Reservation reservation) {
        if(userService.userCheck(reservation.getUser().getId())){
            if(bookService.checkBooks(reservation.getBook().getId())){
                //Se mi trovo qua, so che l' utente ha meno di 3 libri in prestito e che c'è disponibilità del libro
                stockService.bookBorrowed(reservation.getBook().getId());
                return reservationRepository.save(reservation);
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    //ReadOperation
    public List<Reservation> fetchReservationList() {
        return (List<Reservation>) reservationRepository.findAll();
    }

    //updateReservation, usata quando viene ritornato il libro
    public Reservation updateReservation(Reservation reservation, Integer id) {
        Reservation reservationDB = reservationRepository.findById(id).get();

        if (Objects.nonNull(reservation.getReturned()) && !reservation.getReturned().booleanValue()) {
            Iterator<Stock> x = reservation.getBook().getStocks().iterator();
            Stock stock;
            while (x.hasNext()) {
                stock = x.next();
                if (stock.getBook().getId() == reservation.getBook().getId()) {
                    stock.setQuantita(stock.getQuantita() + 1);
                    break;
                }
            }
            reservationDB.setReturned(reservation.getReturned());
        }

        return reservationRepository.save(reservationDB);
    }

    //delete operation
    public void deleteReservationById(Integer id) {
        reservationRepository.deleteById(id);
    }
}
