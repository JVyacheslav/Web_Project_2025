package com.project.Web_Project.controllers;

import com.project.Web_Project.database.ReservationDatabaseManager;
import com.project.Web_Project.dto.Reservation;
import com.project.Web_Project.interfaces.ControllerInterface;
import com.project.Web_Project.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

//Main page
@Controller
@RequestMapping("/")
//Saves user's session
@SessionAttributes(value = "user")
public class MainPage implements ControllerInterface {
    @Autowired
    private ReservationDatabaseManager reservationDatabaseManager;
    //base get method, spring 'autowire' user
    @Override
    public String setForm(User user, Model model) {
        List<Reservation> reservations = reservationDatabaseManager.getAllReservationsByUserEmail(user.getEmail());
        if(!reservations.isEmpty()) {
            model.addAttribute("reservationMessage", "Ваши заявки на оформление поездки:");
            model.addAttribute("userReservations", reservations);
        }
        return "main";
    }
}
