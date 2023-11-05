package com.example.demoservice.controller;


import com.example.demoservice.entity.Reservation;
import com.example.demoservice.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController extends Exception {

    private final ReservationService reservationService;


    //Save operation
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String saveReservation(@Valid @RequestBody Reservation reservation) {
        Reservation rs = reservationService.saveReservation(reservation);
        if(rs==null){
            return "La prenotazione non è andata a buon fine, l' utente ha già più di tre libri oppure la quantità dei libri è esaurita";
        }
        else{
            return "Prenotazione andata a buon fine"+reservation.toString();
        }

    }

    //Read operation
    @RequestMapping(value = "fetch", method = RequestMethod.GET)
    public List<Reservation> fetchReservationList() {
        return reservationService.fetchReservationList();
    }

    //update operation, quando viene ritornato il libro
    @RequestMapping(value = "update/{id:.+}", method = RequestMethod.POST)
    public Reservation updateReservation(@RequestBody Reservation reservation, @PathVariable("id") Integer id) {
        return reservationService.updateReservation(reservation, id);
    }

    @RequestMapping(value = "delete/{id:.+}",method = RequestMethod.POST)
    public String deleteReservation(@PathVariable("id") Integer id) {
        reservationService.deleteReservationById(id);
        return "Deleted Successfully";
    }
}
