package com.project.Web_Project.controllers.Registration;

import com.project.Web_Project.database.DatabaseManager;
import com.project.Web_Project.interfaces.ControllerInterface;
import com.project.Web_Project.utils.User;
import com.project.Web_Project.utils.Validation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


//Registration class - there is base form for user object.
@Controller
@RequestMapping("/reg")
@SessionAttributes(value = "user")
public class Registration implements ControllerInterface {
    //base get method
    @Override
    public String setForm(User user) {
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
        return "redirect:/reg/confirm";
    }


    @GetMapping("/confirm")
    public String addEmail(User user, Model model){
        if(user.isAuth() || user.getRegistrationCode()==null){
            return "redirect:/";
        }
        return "confirm";
    }



    @PostMapping("/confirm")
    public String getEmailCode(@ModelAttribute User user, Model model, DatabaseManager databaseManager){
        System.out.println("User: " + user.getUserInputCode());

        if(user.getUserInputCode().equals(user.getRegistrationCode())){

            if(databaseManager.saveUser(user)) {
                user.setAuth(true);
                return "redirect:/";
            }

        }
        model.addAttribute("validationEmailCode", "Некорректный код");
        return "confirm";
    }
}
