package com.example.demoservice.repository;

import com.example.demoservice.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demoservice.entity.User;


public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
