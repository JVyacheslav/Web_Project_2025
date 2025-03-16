package com.project.Web_Project.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tours")
@AllArgsConstructor
@NoArgsConstructor
public class Tour {
    @Id
    @Column
    private long id;
    @Column(name = "tourname")
    private String tourName;
    @Column(name = "tourdesc")
    private String tourDesc;
    @Column
    private int price;
}
