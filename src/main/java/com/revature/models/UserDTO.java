package com.revature.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private int userId;
    private String userName;
    private BatchDTO batch;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private boolean isDriver;
    private boolean isActive;
    private boolean isAcceptingRides;
    private AddressDTO hAddress;
    private AddressDTO wAddress;

    public UserDTO(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.batch = new BatchDTO(user.getBatch());
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.isDriver = user.isDriver();
        this.isActive = user.isActive();
        this.isAcceptingRides = user.isAcceptingRides();
        this.hAddress = new AddressDTO(user.getHAddress());
        this.wAddress = new AddressDTO(user.getWAddress());
    }
}
