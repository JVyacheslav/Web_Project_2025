package com.project.Web_Project.utils;


import com.project.Web_Project.utils.actions.EmailConfirm;

import javax.mail.*;

public class Validation {
    public String setUpValidation(User user){
        if(isInValidName(user.getUsername())){
            System.out.println(user.getUsername());
            return "Некорректное имя пользователя";
        } else if(isInValidPass(user.getPass())){
            return "Некорректный пароль";
        } else if (isInValidEmail(user)) {
            return "Некорректная электронная почта";
        }
        return null;
    }
    private boolean isInValidName(String username){
        return !(username.length()<31 && username.length()>0);
    }
    private boolean isInValidPass(String pass){
        return !(pass.length()<21 && pass.length()>5);
    }
    private boolean isInValidEmail(User user) {
        try {
            new EmailConfirm(user);
            return false;
        } catch (MessagingException e) {
            return true;
        }
    }
}
