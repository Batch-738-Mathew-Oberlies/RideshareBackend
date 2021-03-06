package com.revature.models;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Admin class that represents the admins. All admins have an id and a username.
 * 
 * @author Adonis Cabreja
 *
 */

@Component
@Entity
@Table(name="admins")
@Data @NoArgsConstructor
public class Admin implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="admin_id")
	private int adminId;
	
	@Column(name = "user_name")
	private String userName;

	public Admin(int adminId, String userName) {
		super();
		this.adminId = adminId;
		this.userName = userName;
	}

	public Admin(AdminDTO adminDTO) {
		super();
		if (adminDTO != null) {
			this.adminId = adminDTO.getAdminId();
			this.userName = adminDTO.getUserName();
		}
	}
}
