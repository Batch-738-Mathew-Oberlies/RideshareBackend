package com.revature.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminDTO {
	private int adminId;
	private String userName;

	public AdminDTO(Admin admin) {
		super();
		if (admin != null) {
			this.adminId = admin.getAdminId();
			this.userName = admin.getUserName();
		}
	}
}
