package com.project.Web_Project.controllers;

import com.project.Web_Project.database.UserDatabaseManager;
import com.project.Web_Project.interfaces.PostControllerInterface;
import com.project.Web_Project.dto.User;
import com.project.Web_Project.service.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


//Registration class - there is base form for user object.
@Controller
@RequestMapping("/reg")
@SessionAttributes(value = "user")
public class Registration implements PostControllerInterface {
    private Validation validation;
    @Autowired
    public void setValidation(Validation validation){
        this.validation=validation;
    }
    private UserDatabaseManager userDatabaseManager;
    @Autowired
    public void setDbManager(UserDatabaseManager userDatabaseManager){
        this.userDatabaseManager = userDatabaseManager;
    }
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
    @Override
    public String getForm(@ModelAttribute User user, Model model){
        String validationText;
        //checks if there is no registered user
        if(userDatabaseManager.selectUser(user.getEmail()) == null) {
            validationText = validation.setUpValidation(user);
            if (validationText != null) {
                System.out.println(user.toString());
                model.addAttribute("valid", validationText);
                return "reg";
            }
        } else{
            validationText = "Пользователь с этой почтой уже зарегистрирован.";
            model.addAttribute("valid", validationText);
            return "reg";
        }
        System.out.println(user.toString());
        return "redirect:/reg/confirm";
    }
}
