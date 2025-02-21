package com.project.Web_Project.utils;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;
//It is User's profile
@Component
@Getter
@Setter
@ToString
public class User {
    public String name = "Гость";
    public String pass;
    public boolean auth;
}
