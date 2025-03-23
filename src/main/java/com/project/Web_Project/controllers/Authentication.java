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
@RequestMapping("/auth")
public class Authentication implements PostControllerInterface {
    @Autowired
    private UserDatabaseManager userDatabaseManager;


    @Override
    public String getForm(@ModelAttribute User user, Model model){
        //checks user
        User realUser = userDatabaseManager.selectUser(user.getEmail());
        if(realUser != null) {
            if (realUser.getPass().equals(user.getPass())) {
                user.setAuth(true);
                user.setUsername(realUser.getUsername());
                return "redirect:/";
            }
        }
        model.addAttribute("valid", "Некорректные данные, попробуйте снова");
        return "passAuth";
    }

    @Override
    public String setForm(User user, Model model) {
        if(user.isAuth()){
            return "redirect:/";
        } else{
            return "passAuth";
        }
    }
}
