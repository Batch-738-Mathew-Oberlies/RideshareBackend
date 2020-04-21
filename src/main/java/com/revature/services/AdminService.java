package com.revature.services;

import java.util.List;

import com.revature.model.Admin;

public interface AdminService {
	
	public List<Admin> getAdmins();
	public Admin getAdminById(int id);
	public Admin createAdmin(Admin admin);
	public Admin updateAdmin(Admin admin);
	public String deleteAdminById(int id);
}
