package com.project.Web_Project.main_logic.base_controllers.forms;

import com.project.Web_Project.database.DatabaseManager;
import com.project.Web_Project.interfaces.PostControllerInterface;
import com.project.Web_Project.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


@SessionAttributes(value = "user")
@Controller
@RequestMapping("/{}/confirm")
public class ConfirmForm implements PostControllerInterface {

    @Override
    public String setForm(User user){
        if(user.isAuth() || user.getCode()==null){
            return "redirect:/";
        }
        return "confirm";
    }

    @Override
    public String getForm(@ModelAttribute User user, Model model, DatabaseManager databaseManager){
        System.out.println("User: " + user.getUserInputCode());

        if(user.getUserInputCode().equals(user.getCode())){
            //checks user
            User realUser = databaseManager.selectUser(user);
            if(realUser != null) {
                user.setAuth(true);
                user.setUsername(realUser.getUsername());
                user.setPass(realUser.getPass());
                return "redirect:/";
            } else if(databaseManager.saveUser(user)) {
                user.setAuth(true);
                return "redirect:/";
            }

        }
        model.addAttribute("validationEmailCode", "Некорректный код");
        return "confirm";
    }

}
