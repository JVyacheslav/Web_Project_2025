package com.project.Web_Project.controllers.Registration;

import com.project.Web_Project.utils.User;
import com.project.Web_Project.utils.Validation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


//Registration class - there is base form for user object.
@Controller
@RequestMapping("/reg")
@SessionAttributes(value = "user")
public class Registration {
    //base get method
    @GetMapping
    public String setForm(User user){
        System.out.println(user.isAuth());
        if(user.isAuth()){
            return "redirect:/";
        } else{
            return "reg";
        }
    }
    //post method, gets info from fields (reg.html) and puts to user object
    @PostMapping
    public String getForm(@ModelAttribute User user, Model model){
        String validation = new Validation().setUpValidation(user);
        if(validation != null){
            System.out.println(user.toString());
            model.addAttribute("valid", validation);
            return "reg";
        }
        System.out.println(user.toString());
        return "redirect:/reg/reg-final";
    }


    @GetMapping("/reg-final")
    public String addEmail(User user, Model model){
        if(user.isAuth()){
            return "redirect:/";
        }
        return "reg-final";
    }
    @PostMapping("/reg-final")
    public String getEmailCode(@ModelAttribute User user, Model model){
        System.out.println("User: " + user.getUserInputCode());
        if(user.getUserInputCode().equals(user.getRegistrationCode())){
            user.setAuth(true);
            return "redirect:/";
        }
        model.addAttribute("validationEmailCode", "Некорректный код");
        return "reg-final";
    }
}
