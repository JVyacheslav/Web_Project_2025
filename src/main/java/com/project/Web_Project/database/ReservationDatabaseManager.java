package com.project.Web_Project.database;

import com.project.Web_Project.dto.Reservation;
import com.project.Web_Project.interfaces.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationDatabaseManager {
    @Autowired
    private ReservationRepository reservationRepository;
    public Reservation saveOrUpdateReservation(Reservation reservation){
        return reservationRepository.save(reservation);
    }
    public List<Reservation> getAllReservationsByUserEmail(String email){
        return reservationRepository.findAllByCustomerEmail(email);
    }
}
