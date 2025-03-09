package com.project.Web_Project.controllers.forms.Auth;

import com.project.Web_Project.database.DatabaseManager;
import com.project.Web_Project.interfaces.PostControllerInterface;
import com.project.Web_Project.utils.User;
import com.project.Web_Project.utils.Validation;
import com.project.Web_Project.utils.actions.EmailConfirm;
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
    @Override
    public String setForm(User user) {
        if(user.isAuth()){
            return "redirect:/";
        } else {
            return "emailAuth";
        }
    }

    @Override
    public String getForm(@ModelAttribute User user, Model model, DatabaseManager databaseManager) throws MessagingException {
        User realUser = databaseManager.selectUser(user);
        if(realUser == null){
            model.addAttribute("valid", "Ошибка, пользователя с такой почтой не существует...");
            return "emailAuth";
        } else {
            new EmailConfirm(user);
            return "redirect:/auth/confirm";
        }
    }
}
