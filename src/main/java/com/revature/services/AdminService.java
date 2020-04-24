package com.revature.services;

import com.revature.models.Admin;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    List<Admin> getAdmins();

    Optional<Admin> getAdminById(int id);

    Admin createAdmin(Admin admin);

    Admin updateAdmin(Admin admin);

    String deleteAdminById(int id);
}
