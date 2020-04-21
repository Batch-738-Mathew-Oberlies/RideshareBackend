package com.revature.model;

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
	
	public Batch() {
		super();
	}

	public Batch(int batchNumber, @NotBlank String batchLocation) {
		super();
		this.batchNumber = batchNumber;
		this.batchLocation = batchLocation;
	}

	public int getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(int batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getBatchLocation() {
		return batchLocation;
	}

	public void setBatchLocation(String batchLocation) {
		this.batchLocation = batchLocation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batchLocation == null) ? 0 : batchLocation.hashCode());
		result = prime * result + batchNumber;
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
		Batch other = (Batch) obj;
		if (batchLocation == null) {
			if (other.batchLocation != null)
				return false;
		} 
		else if (!batchLocation.equals(other.batchLocation))
			return false;
		return batchNumber == other.batchNumber;
	}

	@Override
	public String toString() {
		return "Batch [batchNumber=" + batchNumber + ", batchLocation=" + batchLocation + "]";
	}
	
}
