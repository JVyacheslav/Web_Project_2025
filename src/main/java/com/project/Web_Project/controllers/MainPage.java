package com.project.Web_Project.controllers;

import com.project.Web_Project.utils.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
//Main page
@Controller
@RequestMapping("/")
//Saves user's session
@SessionAttributes(value = "user")
public class MainPage {
    @GetMapping
    public String mainPage(Model model, User user){
        model.addAttribute("user.name", user.getName());
        return "main";
    }
}
