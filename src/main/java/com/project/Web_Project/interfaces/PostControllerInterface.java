package com.project.Web_Project.interfaces;

import com.project.Web_Project.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
//Interface for all controllers with POST Request
@Controller
public interface PostControllerInterface extends ControllerInterface{
    @PostMapping
    public abstract String getForm(@ModelAttribute User user, Model model) throws MessagingException;
}
