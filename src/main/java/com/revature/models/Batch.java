package com.revature.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Batch class that represents a user's batch. All batches have a batch number and a location.
 * 
 * @author Adonis Cabreja
 *
 */

@Component
@Entity
@Table(name="batches")
@Getter @NoArgsConstructor @Setter @EqualsAndHashCode @ToString
public class Batch implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Positive(message="Batch number must be positive.")
	@Column(name="batch_number")
	private int batchNumber;
	
	@NotBlank(message="Batch location cannot be blank.")
	@Column(name="batch_location")
	private String batchLocation;
	
	public Batch(int batchNumber, String batchLocation) {
		super();
		this.batchNumber = batchNumber;
		this.batchLocation = batchLocation;
	}
}
