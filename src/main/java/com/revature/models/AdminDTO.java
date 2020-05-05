package com.revature.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.revature.exceptions.IllegalNullArgumentException;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminDTO {
	private int adminId;

	@NotBlank(message="Username cannot be blank.")
	@Size(min = 3, max = 12, message = "Number of characters must be between 3 and 12.")
	@Pattern(regexp = "^\\w+\\.?\\w+$", message = "Username format is incorrect.")
	private String userName;

	public AdminDTO(Admin admin) {
		super();
		if (admin == null) {
			throw new IllegalNullArgumentException("The AdminDTO requires an admin object to construct");
		}
		this.adminId = admin.getAdminId();
		this.userName = admin.getUserName();
	}
}
