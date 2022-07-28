package com.met.jumbo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Weather {

    @Id
    @Column
    private int id;

    @Column
    private String code;

    @Column
    private String location;

    @Column
    private String zipcode;

    @Column
    private String city;

    @Column
    private String description;

    @Column
    private String temperature;

    @Column
    private String pressure;

    @Column
    private String humidity;

    @Column
    private String wind;

    @Column
    private LocalDate recordDate;

}
