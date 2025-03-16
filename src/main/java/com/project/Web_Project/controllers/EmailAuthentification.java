package com.project.Web_Project.main_logic.base_controllers.forms.Auth;

import com.project.Web_Project.database.DatabaseManager;
import com.project.Web_Project.interfaces.PostControllerInterface;
import com.project.Web_Project.dto.User;
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
    public String getForm(@ModelAttribute User user, Model model, DatabaseManager databaseManager) {
        //checks user in database
        User realUser = databaseManager.selectUser(user);
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
