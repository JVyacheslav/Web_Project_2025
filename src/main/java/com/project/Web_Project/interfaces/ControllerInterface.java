package com.project.Web_Project.interfaces;

import com.project.Web_Project.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//Interface for all controllers with GET Request
@Controller
public interface ControllerInterface {
    @GetMapping
    String setForm(User user, Model model);
}
