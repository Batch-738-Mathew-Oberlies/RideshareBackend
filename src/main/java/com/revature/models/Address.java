package com.revature.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
public class Address implements Serializable {
    private static final long serialVersionUID = 42L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private int id;

    @NotEmpty
    private String street;

    @NotEmpty
    private String city;

    @NotEmpty
    private String state;

    @NotEmpty
    private String zip;
}
