package com.revature.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.revature.models.Batch;

import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Entity
@Table(name="users")
@Getter @NoArgsConstructor @Setter @EqualsAndHashCode @ToString
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
	@Pattern(regexp="^\\w+\\.?\\w+@\\w+\\.[a-zA-Z]{2,4}$")
	private String email;
	@NotBlank
	@Column(name="phone_number")
	@Pattern(regexp="^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$")
	private String phoneNumber;
	@Column(name="is_driver")
	private boolean isDriver;
	@Column(name="is_active")
	private boolean isActive;
	@Column(name="is_accepting_rides")
	private boolean isAcceptingRides;
	@NotBlank
	@Column(name = "h_address")
	private String hAddress;
	@NotBlank
	@Column(name = "h_city")
	private String hCity;
	@NotBlank
	@Column(name = "h_zip")
	private String hZip;
	@NotBlank
	@Column(name = "h_state")
	private String hState;
	@NotBlank
	@Column(name = "w_address")
	private String wAddress;
	@NotBlank
	@Column(name = "w_city")
	private String wCity;
	@NotBlank
	@Column(name = "w_zip")
	private String wZip;
	@NotBlank
	@Column(name = "w_state")
	private String wState;
	
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
			@NotBlank String lastName, @Email String email, @NotBlank String phoneNumber, String hAddress, String hCity,
			String hZip, String hState, String wAddress, String wCity, String wZip, String wState) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.batch = batch;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.hAddress = hAddress;
		this.hCity = hCity;
		this.hZip = hZip;
		this.hState = hState;
		this.wAddress = wAddress;
		this.wCity = wCity;
		this.wZip = wZip;
		this.wState = wState;
	}
	public User(int userId, @NotBlank String userName, Batch batch, @NotBlank String firstName,
			@NotBlank String lastName, @Email String email, @NotBlank String phoneNumber, boolean isDriver,
			boolean isActive, boolean isAcceptingRides, String hAddress, String hCity, String hZip, String hState,
			String wAddress, String wCity, String wZip, String wState) {
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
		this.hCity = hCity;
		this.hZip = hZip;
		this.hState = hState;
		this.wAddress = wAddress;
		this.wCity = wCity;
		this.wZip = wZip;
		this.wState = wState;
	}
}
