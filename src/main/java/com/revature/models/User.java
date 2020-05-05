package com.revature.models;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Entity
@Table(name="users")
@Data @NoArgsConstructor
public class User implements Serializable {
	private static final long serialVersionUID = -4736936444848317674L;

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
	private boolean driver;

	@Column(name = "is_active")
	private boolean active;

	@Column(name = "is_accepting_rides")
	private boolean acceptingRides;

	@OneToOne(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "h_address")
	private Address homeAddress;

	@OneToOne(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "w_address")
	private Address workAddress;


	public User(int userId, String userName, Batch batch, String firstName, String lastName, String email, String phoneNumber,
				boolean driver, boolean active, boolean acceptingRides) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.batch = batch;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.driver = driver;
		this.active = active;
		this.acceptingRides = acceptingRides;
	}

	public User(String userName, Batch batch, String firstName, String lastName, String email, String phoneNumber, boolean driver, boolean active, boolean acceptingRides) {
		super();
		this.userName = userName;
		this.batch = batch;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.driver = driver;
		this.active = active;
		this.acceptingRides = acceptingRides;
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
				String lastName, String email, String phoneNumber, Address homeAddress, Address workAddress) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.batch = batch;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.homeAddress = homeAddress;
		this.workAddress = workAddress;
	}

	public User(int userId, String userName, Batch batch, String firstName,
				String lastName, String email, String phoneNumber, boolean driver,
				boolean active, boolean acceptingRides, Address homeAddress, Address workAddress) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.batch = batch;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.driver = driver;
		this.active = active;
		this.acceptingRides = acceptingRides;
		this.homeAddress = homeAddress;
		this.workAddress = workAddress;

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
			this.driver = userDTO.isDriver();
			this.active = userDTO.isActive();
			this.acceptingRides = userDTO.isAcceptingRides();
			this.homeAddress = new Address(userDTO.getHomeAddress());
			this.workAddress = new Address(userDTO.getWorkAddress());
		}
	}
}
