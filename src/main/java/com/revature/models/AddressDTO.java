package com.revature.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDTO {
    private int id;
    private String street;
    private String city;
    private String state;
    private String zip;

    public AddressDTO(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.city = address.getCity();
        this.state = address.getState();
        this.zip = address.getZip();
    }
}
