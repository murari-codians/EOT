package com.eot.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eot.domain.model.SuperAdmin;
import com.eot.domain.service.SuperAdminService;

/**
 * @author murari 24/08/2018
 *
 */
@RestController
public class SuperAdminController {

	@Autowired
	SuperAdminService superAdminService;

	/* Api to get all superadmins */
	@RequestMapping(value = "api/superadmins", method = RequestMethod.GET)
	public @ResponseBody List<SuperAdmin> getListSuperAdmin() {
		List<SuperAdmin> superAdmins = superAdminService.getListSuperAdmin();

		return superAdmins;
	}

	/* Api to create superadmin */
	@RequestMapping(value = "/api/superadmin", method = RequestMethod.POST)
	public @ResponseBody Object add(@RequestBody SuperAdmin superAdmin) {
		try {
			superAdminService.saveOrUpdate(superAdmin);
			return new String("Superadmin created successfully");

		} catch (EOTException e) {
			e.printStackTrace();
			return new String(e.getMessage());
		}
	}

	/* Api to update superadmin */
	@RequestMapping(value = "api/update/{id}", method = RequestMethod.PUT)
	public @ResponseBody SuperAdmin update(@PathVariable("id") int id, @RequestBody SuperAdmin superAdmin) {
		superAdmin.setId(id);
		try {
			superAdminService.saveOrUpdate(superAdmin);
		} catch (EOTException e) {
			e.printStackTrace();
		}

		return superAdmin;
	}

	/* Api to delete superadmin */
	@RequestMapping(value = "api/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody SuperAdmin Delete(@PathVariable("id") int id) {
		SuperAdmin superAdmin = superAdminService.findSuperAdminById(id);
		superAdminService.deleteSuperAdmin(id);

		return superAdmin;
	}

	/* Api to login superadmin */
	@RequestMapping(value = "/api/admin/login", method = RequestMethod.PUT)
	public ResponseEntity<Object> loginSuperAdmin(@RequestBody SuperAdmin admin) {

		try {
			superAdminService.superAdminLogin(admin);
			return ResponseEntity.status(HttpStatus.OK).body("login Successfully");
		} catch (EOTException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
