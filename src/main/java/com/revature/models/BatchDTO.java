package com.revature.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BatchDTO {
	private int batchNumber;
	private String batchLocation;

	public BatchDTO(Batch batch) {
		super();
		if (batch != null) {
			this.batchNumber = batch.getBatchNumber();
			this.batchLocation = batch.getBatchLocation();
		}
	}
}
