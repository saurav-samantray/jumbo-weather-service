package com.met.jumbo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @Column
    private int id;

    @Column
    private String locality;

    @Column(unique = true)
    private String zipcode;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private String country;
}
