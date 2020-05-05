package com.revature.models;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	private String street;
	private String apt;
	private String city;
	private String state;
	private String zip;

	public Address(AddressDTO addressDTO) {
		super();
		if (addressDTO != null) {
			this.id = addressDTO.getId();
			this.street = addressDTO.getStreet();
			this.apt = addressDTO.getApt();
			this.city = addressDTO.getCity();
			this.state = addressDTO.getState();
			this.zip = addressDTO.getZip();
		}
	}
}
