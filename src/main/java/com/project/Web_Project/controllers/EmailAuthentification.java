package com.project.Web_Project.controllers;

import com.project.Web_Project.database.UserDatabaseManager;
import com.project.Web_Project.interfaces.PostControllerInterface;
import com.project.Web_Project.dto.User;
import com.project.Web_Project.service.EmailConfirm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.mail.MessagingException;

@Controller
@SessionAttributes(value = "user")
@RequestMapping("/auth/emailAuth")
public class EmailAuthentification implements PostControllerInterface {
    private UserDatabaseManager userDatabaseManager;
    @Autowired
    public void setDbManager(UserDatabaseManager userDatabaseManager){
        this.userDatabaseManager = userDatabaseManager;
    }
    @Override
    public String setForm(User user) {
        if(user.isAuth()){
            return "redirect:/";
        } else {
            return "emailAuth";
        }
    }

    @Override
    public String getForm(@ModelAttribute User user, Model model) {
        //checks user in database
        User realUser = userDatabaseManager.selectUser(user.getEmail());
        if(realUser == null){
            model.addAttribute("valid", "Ошибка, пользователя с такой почтой не существует...");
            return "emailAuth";
        } else {
            try {
                //sends email with confirm code
                new EmailConfirm(user);
            } catch (MessagingException e){
                System.err.println(e);
            }
            return "redirect:/auth/confirm";
        }
    }
}
