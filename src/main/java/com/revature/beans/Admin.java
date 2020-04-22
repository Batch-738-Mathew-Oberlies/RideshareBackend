package com.revature.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

/**
 * Admin class that represents the admins. All admins have an id and a username.
 * 
 * @author Adonis Cabreja
 *
 */

@Component
@Entity
@Table(name="admins")
public class Admin implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="admin_id")
	private int adminId;
	
	@NotBlank
	@Column(name="user_name")
	@Size(min=3,max=12)
	@Pattern(regexp="^\\w+\\.?\\w+$")
	private String userName;
	
	/**
	 * A no-args constructor for the Admin model.
	 */
	public Admin() {
		super();
	}
	
	/**
	 * A parameterized constructor for the Admin model.
	 * @param adminId The admin's initialized id.
	 * @param userName The admin's username.
	 */
	public Admin(int adminId, String userName) {
		super();
		this.adminId = adminId;
		this.userName = userName;
	}

	/**
	 * A getter for the admin's id.
	 * @return The admin's id as an int. This is its primary database key.
	 */
	public int getAdminId() {
		return adminId;
	}

	/**
	 * A setter for the admin's id.
	 * @param adminId The new id for the admin. This is its primary database key.
	 */
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	/**
	 * A getter for the admin's username.
	 * @return The admin's username as a String.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * A setter for the admin's username.
	 * @param userName The admin's new username.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * An overridden generate hashcode method for the Admin model.
	 * @return An integer hashcode.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + adminId;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	/**
	 * An overridden equals method for the Admin class.
	 * @return Equality on the basis of username and id between two Admin objects.
	 * @param obj The object to compare the Admin to.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		if (adminId != other.adminId)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} 
		else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	
	/**
	 * An overridden toString method for the Admin model.
	 * @return A human readable String representation of the Admin object including
	 *         their username and id.
	 */
	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", userName=" + userName + "]";
	}
	
}
