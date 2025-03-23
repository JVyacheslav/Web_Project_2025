package com.project.Web_Project.controllers;

import com.project.Web_Project.database.ReservationDatabaseManager;
import com.project.Web_Project.database.ToursDatabaseManager;
import com.project.Web_Project.dto.Reservation;
import com.project.Web_Project.dto.User;
import com.project.Web_Project.service.validation.ReservationValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@SessionAttributes( names ="user")
@RequestMapping("/searchTour/makingOrder")
@Controller
public class TourOrderController {
    @Autowired
    private ReservationDatabaseManager reservationDatabaseManager;
    @Autowired
    private ToursDatabaseManager toursDatabaseManager;
    @Autowired
    private ReservationValidation reservationValidation;


    @PostMapping
    public String getForm(User user, Model model, @ModelAttribute Reservation reservation, @RequestParam long tourId) {
        if(reservationValidation.setUpReservationValidation(reservation)) {
            reservation.setPrice(reservation.getPrice() * (reservation.getNumOfAdults() + reservation.getNumOfChildren()));
            reservation.setCustomerEmail(user.getEmail());
            reservation.setTourName(toursDatabaseManager.selectById(tourId).get().getTourName());
            reservationDatabaseManager.saveOrUpdateReservation(reservation);
            return "redirect:/";
        } else {
            model.addAttribute("validation", "Ошибка, некорректные данные ввода!");
            return "makingOrder";
        }
    }
    @GetMapping
    public String setForm(User user, Reservation reservation, Model model, @RequestParam long tourId){
        if(user.isAuth() && toursDatabaseManager.selectById(tourId).isPresent()) {
            model.addAttribute("reservation", reservation);
            reservationValidation.setMinAndMaxDates(model);
            return "makingOrder";
        } else{
            return "redirect:/";
        }
    }
}