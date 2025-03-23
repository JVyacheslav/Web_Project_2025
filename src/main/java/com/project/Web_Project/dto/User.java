package com.project.Web_Project.dto;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;
//It is User's profile
@Component
@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User {
    @Column
    private String username;
    @Column
    private String pass;
    @Id
    @Column
    private String email;
    @Column
    private String role = "user";


    @Transient
    private boolean auth;
    @Transient
    private String Code;
    @Transient
    private String userInputCode;
}