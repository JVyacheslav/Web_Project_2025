package com.project.Web_Project.controllers.Registration;

import com.project.Web_Project.utils.User;
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
        user.setAuth(true);
        System.out.println(user.toString());
        return "redirect:/";
    }
}
