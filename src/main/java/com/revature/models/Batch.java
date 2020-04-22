package com.revature.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Batch class that represents a user's batch. All batches have a batch number and a location.
 * 
 * @author Adonis Cabreja
 *
 */

@Component
@Entity
@Table(name="batches")
@NoArgsConstructor @Data
public class Batch implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="batch_number")
	private int batchNumber;
	
	@NotBlank
	@Column(name="batch_location")
	private String batchLocation;
	
	public Batch(int batchNumber, @NotBlank String batchLocation) {
		super();
		this.batchNumber = batchNumber;
		this.batchLocation = batchLocation;
	}
}
