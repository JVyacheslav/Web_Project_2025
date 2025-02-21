package com.project.Web_Project.controllers.Registration;

import com.project.Web_Project.utils.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


//Registration class - there is base form for user object.
@Controller
@RequestMapping("/reg")
public class Registration {
    @GetMapping
    public String setForm(User user){
        return "reg";
    }

    @PostMapping
    public String getForm(@ModelAttribute User user, Model model){
        System.out.println(user.toString());
        return "main";
    }
}
