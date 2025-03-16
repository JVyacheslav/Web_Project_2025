package com.project.Web_Project.service;


import com.project.Web_Project.database.ToursDatabaseManager;
import com.project.Web_Project.dto.Tour;
import com.project.Web_Project.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Component
public class Validation {
    private ToursDatabaseManager toursDatabaseManager;
    @Autowired
    public Validation(ToursDatabaseManager toursDatabaseManager){
        this.toursDatabaseManager=toursDatabaseManager;
    }
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
    //simple name validation
    private boolean isInValidName(String username){
        return !(username.length()<31 && username.length()>0);
    }
    //simple pass validation
    private boolean isInValidPass(String pass){
        return !(pass.length()<21 && pass.length()>5);
    }
    //sends email with confirm code to validate user
    private boolean isInValidEmail(User user) {
        try {
            new EmailConfirm(user);
            return false;
        } catch (MessagingException e) {
            return true;
        }
    }
    public List<Tour> getToursByParam(int param){
        List<Tour> tours = toursDatabaseManager.selectAll();
        List<Tour> result = new ArrayList<>();
        int i = (param-1)*6, k = param*6;
        while(i >= 0 && i < tours.size() && i < k) {
            result.add(tours.get(i));
            i++;
        }
        return result;
    }
}
