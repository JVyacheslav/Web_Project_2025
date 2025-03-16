package com.project.Web_Project.main_logic.base_controllers;

import com.project.Web_Project.interfaces.ControllerInterface;
import com.project.Web_Project.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
//Main page
@Controller
@RequestMapping("/")
//Saves user's session
@SessionAttributes(value = "user")
public class MainPage implements ControllerInterface {
    //base get method, spring 'autowire' user
    @Override
    public String setForm(User user) {
        return "main";
    }
}
