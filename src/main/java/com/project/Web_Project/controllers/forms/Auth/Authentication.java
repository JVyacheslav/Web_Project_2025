package com.project.Web_Project.controllers.Auth;


import com.project.Web_Project.interfaces.ControllerInterface;
import com.project.Web_Project.utils.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class Authentication implements ControllerInterface {
    @PostMapping
    public String getForm(){
        return "redirect:/";
    }

    @Override
    public String setForm(User user) {
        if(user.isAuth()){
            return "redirect:/";
        }
        return "auth";
    }
}
