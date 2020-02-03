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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import org.springframework.stereotype.Component;

/**
 * User class that represents a driver/rider. All users have an id, username, corresponding batch, first name,
 * last name, email, phone number, isDriver, isActive and isAcceptingRides.
 * 
 * @author Adonis Cabreja
 *
 */

@Component
@Entity
@Table(name="users2")
public class User2 implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;
	
	@NotBlank
	@Column(name="user_name")
	private String userName;
	
	@ManyToOne
	@JoinColumn(name="batch_number")
	private Batch batch;
	
	@NotBlank
	@Column(name="first_name")
	private String firstName;
	
	@NotBlank
	@Column(name="last_name")
	private String lastName;
	
	@Email
	private String email;
	
	@NotBlank
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="is_driver")
	private boolean isDriver;
	
	@Column(name="is_active")
	private boolean isActive;
	
	@Column(name="is_accepting_rides")
	private boolean isAcceptingRides;
	
	public User2() {
		super();
	}
	
	public User2(int userId, String userName, Batch batch, String firstName, String lastName, String email,
			String phoneNumber) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.batch = batch;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batch == null) ? 0 : batch.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + (isAcceptingRides ? 1231 : 1237);
		result = prime * result + (isActive ? 1231 : 1237);
		result = prime * result + (isDriver ? 1231 : 1237);
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + userId;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		User2 other = (User2) obj;
		if (batch == null) {
			if (other.batch != null)
				return false;
		} 
		else if (!batch.equals(other.batch))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} 
		else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} 
		else if (!firstName.equals(other.firstName))
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
		} 
		else if (!lastName.equals(other.lastName))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} 
		else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (userId != other.userId)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} 
		else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserOriginal [userId=" + userId + ", userName=" + userName + ", batch=" + batch + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", isDriver="
				+ isDriver + ", isActive=" + isActive + ", isAcceptingRides=" + isAcceptingRides + "]";
	}
	
}
