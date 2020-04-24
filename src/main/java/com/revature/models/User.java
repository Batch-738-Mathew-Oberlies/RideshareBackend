package com.revature.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

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

	@Valid
	@NotBlank(message="Username cannot be blank.")
	@Column(name="user_name")
	@Size(min=3,max=12, message="Number of characters must be between 3 and 12.")
	@Pattern(regexp="^\\w+\\.?\\w+$", message="Username format is incorrect.")
	private String userName;

	@ManyToOne
	@JoinColumn(name="batch_number")
	private Batch batch;
	
	@Valid
	@NotBlank(message="First name cannot be blank.")
	@Column(name="first_name")
	@Size(max=30, message= "Number of characters cannot be larger than 30.")
	@Pattern(regexp="^[a-zA-Z\\u00C0-\\u017F]+[- ]?[a-zA-Z\\u00C0-\\u017F]+$", message="First name format is incorrect")
	private String firstName;
	
	@Valid
	@NotBlank(message="Last name cannot be blank.")
	@Column(name="last_name")
	@Size(max=30, message="Number of characters cannot be larger than 30.")
	@Pattern(regexp="^[a-zA-Z\\u00C0-\\u017F]+[- ]?[a-zA-Z\\u00C0-\\u017F]+$", message="Last name format is incorrect")
	private String lastName;

	@NotBlank(message="Email cannot be blank.")
	@Email(message="Email format is incorrect.")
	@Pattern(regexp = "^\\w+\\.?\\w+@\\w+\\.[a-zA-Z]{2,4}$", message="Email format is incorrect.")
	private String email;

	@NotBlank(message="Phone number cannot be blank.")
	@Column(name = "phone_number")
	@Pattern(regexp = "^\\d{3}-\\d{3}-\\d{4}$", message="Phone number format is incorrect.")
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
