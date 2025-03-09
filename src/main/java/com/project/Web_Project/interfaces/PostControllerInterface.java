package com.project.Web_Project.interfaces;

import com.project.Web_Project.database.DatabaseManager;
import com.project.Web_Project.utils.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;

@Controller
public interface PostControllerInterface extends ControllerInterface{
    @PostMapping
    public abstract String getForm(@ModelAttribute User user, Model model, DatabaseManager databaseManager) throws MessagingException;
}
