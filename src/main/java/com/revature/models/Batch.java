package com.revature.models;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
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
@Table(name = "batches")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Batch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "batch_number")
	private int batchNumber;

	@Column(name = "batch_location")
	private String batchLocation;

	public Batch(BatchDTO batchDTO) {
		super();
		if (batchDTO != null) {
			this.batchNumber = batchDTO.getBatchNumber();
			this.batchLocation = batchDTO.getBatchLocation();
		}
	}
}
