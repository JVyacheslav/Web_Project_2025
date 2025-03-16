package com.project.Web_Project.controllers;

import com.project.Web_Project.database.UserDatabaseManager;
import com.project.Web_Project.interfaces.PostControllerInterface;
import com.project.Web_Project.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


@SessionAttributes(value = "user")
@Controller
@RequestMapping("/{}/confirm")
public class ConfirmForm implements PostControllerInterface {
    private UserDatabaseManager userDatabaseManager;
    @Autowired
    public void setDbManager(UserDatabaseManager userDatabaseManager){
        this.userDatabaseManager = userDatabaseManager;
    }
    @Override
    public String setForm(User user){
        if(user.isAuth() || user.getCode()==null){
            return "redirect:/";
        }
        return "confirm";
    }

    @Override
    public String getForm(@ModelAttribute User user, Model model){
        System.out.println("User: " + user.getUserInputCode());

        if(user.getUserInputCode().equals(user.getCode())){
            //checks user
            User realUser = userDatabaseManager.selectUser(user.getEmail());
            if(realUser != null) {
                user.setAuth(true);
                user.setUsername(realUser.getUsername());
                user.setPass(realUser.getPass());
                return "redirect:/";
            } else {
                try {
                    userDatabaseManager.saveUser(user);
                    user.setAuth(true);
                    return "redirect:/";
                } catch (Exception e){
                    System.err.println(e);
                }
            }

        }
        model.addAttribute("validationEmailCode", "Некорректный код");
        return "confirm";
    }

}
