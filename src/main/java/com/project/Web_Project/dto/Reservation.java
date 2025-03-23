package com.project.Web_Project.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "reservation")
@Getter
@Setter
public class Reservation {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private long tourId;
    @Column
    private String tourName;
    @Column
    private String customerEmail;
    @Column
    private int price;
    @Column
    private int numOfAdults;
    @Column
    private int numOfChildren;
    @Column
    private String date;
    @Column
    private String accommodation;
    @Column
    private String town;
    @Column
    private int numOfDays;
    @Column
    private String status = "Не рассмотрен";
}
