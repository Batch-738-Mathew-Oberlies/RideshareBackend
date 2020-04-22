package com.revature.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

/**
 * Batch class that represents a user's batch. All batches have a batch number and a location.
 * 
 * @author Adonis Cabreja
 *
 */

@Component
@Entity
@Table(name="batches")
public class Batch implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="batch_number")
	private int batchNumber;
	
	@NotBlank
	@Column(name="batch_location")
	private String batchLocation;
	
	/**
	 * A no-args constructor for the Batch model.
	 */
	public Batch() {
		super();
	}

	/**
	 * A parameterized constructor for the Batch model.
	 * @param batchNumber The initial value for the batchNumber field. This is its database primary key.
	 * @param batchLocation The initial value for the batchLocation field.
	 */
	public Batch(int batchNumber, @NotBlank String batchLocation) {
		super();
		this.batchNumber = batchNumber;
		this.batchLocation = batchLocation;
	}

	/**
	 * A getter for the batchNumber field.
	 * @return The current batchNumber as an int. This is the database primary key.
	 */
	public int getBatchNumber() {
		return batchNumber;
	}

	/**
	 * A setter for the batchNumber field.
	 * @param batchNumber The new value for the batchNumber field. This is the database primary key.
	 */
	public void setBatchNumber(int batchNumber) {
		this.batchNumber = batchNumber;
	}

	/**
	 * A getter for the getBatchLocation field.
	 * @return The current batch
	 */
	public String getBatchLocation() {
		return batchLocation;
	}

	/**
	 * A setter for the batchLocation field.
	 * @param batchLocation The new batchLocation field.
	 */
	public void setBatchLocation(String batchLocation) {
		this.batchLocation = batchLocation;
	}

	/**
	 * An overridden hashCode method for the Batch model.
	 * @return The object's hash code as an int.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batchLocation == null) ? 0 : batchLocation.hashCode());
		result = prime * result + batchNumber;
		return result;
	}
	
	/**
	 * An overridden equals method for the Batch class.
	 * @return Equality on the basis of batchNumber and batchLocation between two Batch objects.
	 * @param obj The object to compare the Batch to.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Batch other = (Batch) obj;
		if (batchLocation == null) {
			if (other.batchLocation != null)
				return false;
		} 
		else if (!batchLocation.equals(other.batchLocation))
			return false;
		return batchNumber == other.batchNumber;
	}

	/**
	 * An overridden toString method for the Batch model.
	 * @return A human readable String representation of the Batch object including
	 *         its batchNumber and batchLocation.
	 */
	@Override
	public String toString() {
		return "Batch [batchNumber=" + batchNumber + ", batchLocation=" + batchLocation + "]";
	}
	
}
