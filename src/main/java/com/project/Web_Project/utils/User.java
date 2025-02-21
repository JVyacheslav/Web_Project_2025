package com.project.Web_Project.utils;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
public class User {
    public String name = "Гость";
    public String pass;
}
