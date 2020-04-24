package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

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
	@PositiveOrZero(message = "Batch number must be a nonnegative number.")
	@Column(name = "batch_number")
	private int batchNumber;

	@NotBlank(message = "Batch location cannot be blank.")
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
