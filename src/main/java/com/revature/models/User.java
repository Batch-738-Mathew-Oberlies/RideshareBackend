package com.revature.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Entity
@Table(name="users")
@NoArgsConstructor @Data
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;

	@Column(name="user_name")
	private String userName;

	@ManyToOne
	@JoinColumn(name="batch_number")
	private Batch batch;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;

	private String email;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "is_driver")
	private boolean isDriver;

	@Column(name = "is_active")
	private boolean isActive;

	@Column(name = "is_accepting_rides")
	private boolean isAcceptingRides;

	@OneToOne(cascade = CascadeType.ALL)
	@Valid
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "h_address")
	private Address hAddress;

	@OneToOne(cascade = CascadeType.ALL)
	@Valid
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "w_address")
	private Address wAddress;


	public User(int userId, String userName, Batch batch, String firstName, String lastName, String email, String phoneNumber,
				boolean isDriver, boolean isActive, boolean isAcceptingRides) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.batch = batch;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.isDriver = isDriver;
		this.isActive = isActive;
		this.isAcceptingRides = isAcceptingRides;
	}

	public User(String userName, Batch batch, String firstName, String lastName, String email, String phoneNumber, boolean isDriver, boolean isActive, boolean isAcceptingRides) {
		super();
		this.userName = userName;
		this.batch = batch;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.isDriver = isDriver;
		this.isActive = isActive;
		this.isAcceptingRides = isAcceptingRides;
	}

	public User(int userId, String userName, Batch batch, String firstName, String lastName, String email, String phoneNumber) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.batch = batch;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public User(int userId, String userName, Batch batch, String firstName,
				String lastName, String email, String phoneNumber, Address hAddress, Address wAddress) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.batch = batch;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.hAddress = hAddress;
		this.wAddress = wAddress;
	}

	public User(int userId, String userName, Batch batch, String firstName,
				String lastName, String email, String phoneNumber, boolean isDriver,
				boolean isActive, boolean isAcceptingRides, Address hAddress, Address wAddress) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.batch = batch;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.isDriver = isDriver;
		this.isActive = isActive;
		this.isAcceptingRides = isAcceptingRides;
		this.hAddress = hAddress;
		this.wAddress = wAddress;

	}

	public User(UserDTO userDTO) {
		super();
		if (userDTO != null) {
			this.userId = userDTO.getUserId();
			this.userName = userDTO.getUserName();
			this.batch = new Batch(userDTO.getBatch());
			this.firstName = userDTO.getFirstName();
			this.lastName = userDTO.getLastName();
			this.email = userDTO.getEmail();
			this.phoneNumber = userDTO.getPhoneNumber();
			this.isDriver = userDTO.isDriver();
			this.isActive = userDTO.isActive();
			this.isAcceptingRides = userDTO.isAcceptingRides();
			this.hAddress = new Address(userDTO.getHAddress());
			this.wAddress = new Address(userDTO.getWAddress());
		}
	}
}
