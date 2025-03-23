package com.project.Web_Project.dto;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "tours")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Tour {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "tourname")
    private String tourName;
    @Column(name = "tourdesc")
    private String tourDesc;
}
