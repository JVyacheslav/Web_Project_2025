package com.project.Web_Project.main_logic.base_controllers.forms.Registration;

import com.project.Web_Project.database.DatabaseManager;
import com.project.Web_Project.interfaces.PostControllerInterface;
import com.project.Web_Project.dto.User;
import com.project.Web_Project.utils.Validation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


//Registration class - there is base form for user object.
@Controller
@RequestMapping("/reg")
@SessionAttributes(value = "user")
public class Registration implements PostControllerInterface {
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
    public String getForm(@ModelAttribute User user, Model model, DatabaseManager databaseManager){
        String validation;
        //checks if there is no registered user
        if(databaseManager.selectUser(user) == null) {
            validation = new Validation().setUpValidation(user);
            if (validation != null) {
                System.out.println(user.toString());
                model.addAttribute("valid", validation);
                return "reg";
            }
        } else{
            validation = "Пользователь с этой почтой уже зарегистрирован.";
            model.addAttribute("valid", validation);
            return "reg";
        }
        System.out.println(user.toString());
        return "redirect:/reg/confirm";
    }
}
