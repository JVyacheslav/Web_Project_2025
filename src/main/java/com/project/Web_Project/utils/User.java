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
    public String username;
    public String pass;
    public String email;
    public boolean auth;
    public String registrationCode;
    public String userInputCode;
}
