package com.project.Web_Project.main_logic.base_controllers.forms.Auth;


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
@RequestMapping("/auth")
public class Authentication implements PostControllerInterface {
    @Override
    public String getForm(@ModelAttribute User user, Model model, DatabaseManager databaseManager){
        //checks user
        User realUser = databaseManager.selectUser(user);
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
    public String setForm(User user) {
        if(user.isAuth()){
            return "redirect:/";
        } else{
            return "passAuth";
        }
    }
}
