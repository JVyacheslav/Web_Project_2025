package com.project.Web_Project.interfaces;

import com.project.Web_Project.utils.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public interface ControllerInterface {
    @GetMapping
    public String setForm(User user);
}
