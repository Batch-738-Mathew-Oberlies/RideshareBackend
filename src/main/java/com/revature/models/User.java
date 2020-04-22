package com.revature.models;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

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

	@Valid
	@NotBlank
	@Column(name="user_name")
	@Size(min=3,max=12)
	@Pattern(regexp="^\\w+\\.?\\w+$")
	private String userName;

	@ManyToOne
	@JoinColumn(name="batch_number")
	private Batch batch;
	
	@Valid
	@NotBlank
	@Column(name="first_name")
	@Size(max=30)
	@Pattern(regexp="^[a-zA-Z\\u00C0-\\u017F]+[- ]?[a-zA-Z\\u00C0-\\u017F]+$")
	private String firstName;
	
	@Valid
	@NotBlank
	@Column(name="last_name")
	@Size(max=30)
	@Pattern(regexp="^[a-zA-Z\\u00C0-\\u017F]+[- ]?[a-zA-Z\\u00C0-\\u017F]+$")
	private String lastName;

	@NotBlank
	@Email
	@Pattern(regexp = "^\\w+\\.?\\w+@\\w+\\.[a-zA-Z]{2,4}$")
	private String email;

	@NotBlank
	@Column(name = "phone_number")
	@Pattern(regexp = "^\\d{3}-\\d{3}-\\d{4}$")
	private String phoneNumber;

	@Column(name = "is_driver")
	private boolean isDriver;

	@Column(name = "is_active")
	private boolean isActive;

	@Column(name = "is_accepting_rides")
	private boolean isAcceptingRides;

	@OneToOne(cascade = CascadeType.ALL)
	@NotNull
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "h_address")
	private Address hAddress;

	@OneToOne(cascade = CascadeType.ALL)
	@NotNull
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "w_address")
	private Address wAddress;


	public User(int userId, @NotBlank @Size(min = 3, max = 12) @Pattern(regexp = "^\\w+\\.?\\w+$") String userName,
				Batch batch,
				@NotBlank @Size(max = 30) @Pattern(regexp = "^[a-zA-Z]+-?[a-zA-Z]+ ?[a-zA-Z]+-?[a-zA-Z]+$") String firstName,
				@NotBlank @Size(max = 30) @Pattern(regexp = "^[a-zA-Z]+-?[a-zA-Z]+ ?[a-zA-Z]+-?[a-zA-Z]+$") String lastName,
				@Email @Pattern(regexp = "^\\w+\\.?\\w+@\\w+\\.[a-zA-Z]{2,4}$") String email,
				@NotBlank @Pattern(regexp = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$") String phoneNumber,
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

	public User(@NotBlank @Size(min = 3, max = 12) @Pattern(regexp = "^\\w+\\.?\\w+$") String userName, Batch batch,
			@NotBlank @Size(max = 30) @Pattern(regexp = "^[a-zA-Z]+-?[a-zA-Z]+ ?[a-zA-Z]+-?[a-zA-Z]+$") String firstName,
			@NotBlank @Size(max = 30) @Pattern(regexp = "^[a-zA-Z]+-?[a-zA-Z]+ ?[a-zA-Z]+-?[a-zA-Z]+$") String lastName,
			@Email @Pattern(regexp = "^\\w+\\.?\\w+@\\w+\\.[a-zA-Z]{2,4}$") String email,
			@NotBlank @Pattern(regexp = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$") String phoneNumber,
			boolean isDriver, boolean isActive, boolean isAcceptingRides) {
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

	public User(int userId, @NotBlank @Size(min = 3, max = 12) @Pattern(regexp = "^\\w+\\.?\\w+$") String userName,
			Batch batch,
			@NotBlank @Size(max = 30) @Pattern(regexp = "^[a-zA-Z]+-?[a-zA-Z]+ ?[a-zA-Z]+-?[a-zA-Z]+$") String firstName,
			@NotBlank @Size(max = 30) @Pattern(regexp = "^[a-zA-Z]+-?[a-zA-Z]+ ?[a-zA-Z]+-?[a-zA-Z]+$") String lastName,
			@Email @Pattern(regexp = "^\\w+\\.?\\w+@\\w+\\.[a-zA-Z]{2,4}$") String email,
			@NotBlank @Pattern(regexp = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$") String phoneNumber) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.batch = batch;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public User(int userId, @NotBlank String userName, Batch batch, @NotBlank String firstName,
				@NotBlank String lastName, @Email String email, @NotBlank String phoneNumber, Address hAddress, Address wAddress) {
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

	public User(int userId, @NotBlank String userName, Batch batch, @NotBlank String firstName,
				@NotBlank String lastName, @Email String email, @NotBlank String phoneNumber, boolean isDriver,
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
}
