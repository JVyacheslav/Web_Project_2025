package com.project.Web_Project.interfaces;

import com.project.Web_Project.dto.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    public List<Reservation> findAllByCustomerEmail(String email);
}
