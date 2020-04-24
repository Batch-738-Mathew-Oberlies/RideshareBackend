package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {
	private static final long serialVersionUID = 42L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "address_id")
	private int id;

	@NotBlank(message = "Street cannot be blank.")
	private String street;

	@NotBlank(message = "City cannot be blank.")
	private String city;

	@NotBlank(message = "State cannot be blank.")
	private String state;

	@NotBlank(message = "Zipcode cannot be blank.")
	private String zip;

	public Address(AddressDTO addressDTO) {
		super();
		if (addressDTO != null) {
			this.id = addressDTO.getId();
			this.street = addressDTO.getStreet();
			this.city = addressDTO.getCity();
			this.state = addressDTO.getState();
			this.zip = addressDTO.getZip();
		}
	}
}
