package com.revature.controllers;

import com.revature.models.Admin;
import com.revature.models.AdminDTO;
import com.revature.services.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * AdminController takes care of handling our requests to /admins.
 * It provides methods that can perform tasks like all admins, admin by id, add admin, update admin, and
 * delete admin by id.
 * 
 * @author Adonis Cabreja
 *
 */

@RestController
@RequestMapping("/admins")
@CrossOrigin
@Api(tags= {"Admin"})
public class AdminController {

	@Autowired
	private AdminService adminService;

	/**
	 * HTTP GET method (/admins)
	 *
	 * @return A list of all the admins.
	 */
	@ApiOperation(value="Returns all admins", tags= {"Admin"})
	@GetMapping
	public List<Admin> getAdmins() {

		return adminService.getAdmins();
	}

	/**
	 * HTTP GET method (/admins/{id})
	 *
	 * @param id represents the admin's id.
	 * @return An admin that matches the id.
	 */
	@ApiOperation(value = "Returns admin by id", tags = {"Admin"})
	@GetMapping("/{id}")
	public ResponseEntity<Admin> getAdminById(@PathVariable("id") int id) {
		Optional<Admin> admin = adminService.getAdminById(id);
		return admin.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * HTTP POST method (/admins)
	 *
	 * @param adminDTO represents the new Admin object being sent.
	 * @return The newly created object with a 201 code.
	 */
	@ApiOperation(value = "Adds a new admin", tags = {"Admin"})
	@PostMapping
	public ResponseEntity<Admin> createAdmin(@Valid @RequestBody AdminDTO adminDTO) {
		Admin admin = new Admin(adminDTO);
		return new ResponseEntity<>(adminService.createAdmin(admin), HttpStatus.CREATED);
	}

	/**
	 * HTTP PUT method (/admins)
	 *
	 * @param adminDTO represents the updated Admin object being sent.
	 * @return The newly updated object.
	 */
	@ApiOperation(value = "Updates admin by id", tags = {"Admin"})
	@PutMapping("/{id}")
	public Admin updateAdmin(@Valid @RequestBody AdminDTO adminDTO) {
		Admin admin = new Admin(adminDTO);
		return adminService.updateAdmin(admin);
	}

	/**
	 * HTTP DELETE method (/admins/{id})
	 *
	 * @param id represents the admin's id.
	 * @return A string that says which admin was deleted.
	 */
	@ApiOperation(value="Deletes an admin by id", tags= {"Admin"})
	@DeleteMapping("/{id}")
	public String deleteAdmin(@PathVariable("id") int id) {

		return adminService.deleteAdminById(id);
	}
}
