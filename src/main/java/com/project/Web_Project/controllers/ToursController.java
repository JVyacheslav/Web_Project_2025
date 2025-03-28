package com.project.Web_Project.controllers;

import com.project.Web_Project.dto.User;
import com.project.Web_Project.dto.Tour;
import com.project.Web_Project.service.TourActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Scope(value = "prototype")
@Controller
@RequestMapping("/searchTour")
@SessionAttributes("user")
public class ToursController {

    @Autowired
    private TourActions toursByParam;
    @GetMapping
    public String setForm(User user, Model model, @RequestParam int page){
        if(user.isAuth()){
            List<Tour> tours = toursByParam.getToursByParam(page);
            if(tours.size() > 0) {
                model.addAttribute("page", page);
                model.addAttribute("tours", tours);
                return "tours";
            }
        } else {
            return "redirect:/";
        }
        return "redirect:/searchTour?page=1";

    }
}