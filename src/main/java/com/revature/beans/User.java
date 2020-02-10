package com.revature.beans;

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


import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="users")
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
	
	public User() {
		super();
	}


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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Batch getBatch() {
		return batch;
	}
	public void setBatch(Batch batch) {
		this.batch = batch;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public boolean isDriver() {
		return isDriver;
	}
	public void setDriver(boolean isDriver) {
		this.isDriver = isDriver;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public boolean isAcceptingRides() {
		return isAcceptingRides;
	}
	public void setAcceptingRides(boolean isAcceptingRides) {
		this.isAcceptingRides = isAcceptingRides;
	}

	public String gethAddress() {
		return hAddress;
	}


	public void sethAddress(String hAddress) {
		this.hAddress = hAddress;
	}


	public String gethCity() {
		return hCity;
	}


	public void sethCity(String hCity) {
		this.hCity = hCity;
	}


	public String gethZip() {
		return hZip;
	}


	public void sethZip(String hZip) {
		this.hZip = hZip;
	}


	public String gethState() {
		return hState;
	}


	public void sethState(String hState) {
		this.hState = hState;
	}


	public String getwAddress() {
		return wAddress;
	}


	public void setwAddress(String wAddress) {
		this.wAddress = wAddress;
	}


	public String getwCity() {
		return wCity;
	}


	public void setwCity(String wCity) {
		this.wCity = wCity;
	}


	public String getwZip() {
		return wZip;
	}


	public void setwZip(String wZip) {
		this.wZip = wZip;
	}


	public String getwState() {
		return wState;
	}


	public void setwState(String wState) {
		this.wState = wState;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batch == null) ? 0 : batch.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((hAddress == null) ? 0 : hAddress.hashCode());
		result = prime * result + ((hCity == null) ? 0 : hCity.hashCode());
		result = prime * result + ((hState == null) ? 0 : hState.hashCode());
		result = prime * result + ((hZip == null) ? 0 : hZip.hashCode());
		result = prime * result + (isAcceptingRides ? 1231 : 1237);
		result = prime * result + (isActive ? 1231 : 1237);
		result = prime * result + (isDriver ? 1231 : 1237);
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + userId;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((wAddress == null) ? 0 : wAddress.hashCode());
		result = prime * result + ((wCity == null) ? 0 : wCity.hashCode());
		result = prime * result + ((wState == null) ? 0 : wState.hashCode());
		result = prime * result + ((wZip == null) ? 0 : wZip.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (batch == null) {
			if (other.batch != null)
				return false;
		} else if (!batch.equals(other.batch))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))

			return false;
		if (isAcceptingRides != other.isAcceptingRides)
			return false;
		if (isActive != other.isActive)
			return false;
		if (isDriver != other.isDriver)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (userId != other.userId)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))

			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", batch=" + batch + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", isDriver="
				+ isDriver + ", isActive=" + isActive + ", isAcceptingRides=" + isAcceptingRides + ", hAddress="
				+ hAddress + ", hCity=" + hCity + ", hZip=" + hZip + ", hState=" + hState + ", wAddress=" + wAddress
				+ ", wCity=" + wCity + ", wZip=" + wZip + ", wState=" + wState + "]";
	}

}
