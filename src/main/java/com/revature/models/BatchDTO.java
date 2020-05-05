package com.revature.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

import com.revature.exceptions.IllegalNullArgumentException;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BatchDTO {

	@PositiveOrZero(message = "Batch number must be a nonnegative number.")
	private int batchNumber;

	@NotBlank(message = "Batch location cannot be blank.")
	@Pattern(regexp = "[a-zA-Z0-9 ,]+", message = "Batch location may only contain letters, numbers, spaces, and commas")
	private String batchLocation;

	public BatchDTO(Batch batch) {
		super();
		if(batch == null) {
			throw new IllegalNullArgumentException("BatchDTO requires a batch to construct.");
		}
		this.batchNumber = batch.getBatchNumber();
		this.batchLocation = batch.getBatchLocation();
	}
}
