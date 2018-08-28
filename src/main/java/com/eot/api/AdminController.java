package com.eot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eot.core.EOTConstant;
import com.eot.domain.model.SuperAdmin;
import com.eot.domain.services.SuperAdminService;
import com.eot.util.EotException;


@Controller
public class AdminController {

	
	@Autowired
	SuperAdminService superAdminService;
	
	/*Api to create superadmin*/
	@RequestMapping(value = "/api/admin",method = RequestMethod.POST)
	public ResponseEntity<Object> saveSuperAdmin(@RequestBody SuperAdmin admin) {
		try {

			superAdminService.saveOrUpadte(admin);
			return ResponseEntity.status(HttpStatus.OK).body(admin);

		} catch (EotException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String(e.getMessage()));

		}
	}
//	/*Api to login superadmin*/
//	@RequestMapping(value = "/api/admin/login",method = RequestMethod.PUT)
//	public ResponseEntity<Object> loginAdmin(@RequestBody SuperAdmin admin) {
//
//		try {
//			superAdminService.adminLogin(admin);
//			return ResponseEntity.status(HttpStatus.OK).body(EOTConstant.SUPERADMIN_LOGIN_SUCESS);
//		} catch (EotException e) {
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//		}
//
//	}
	/*Api to delete superadmin*/
	@RequestMapping(value = "/api/admin/delete/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteAdmin(@PathVariable("userId") String userId){
		
		try {
			
			superAdminService.deleteAdmin(userId);
			
			return ResponseEntity.status(HttpStatus.OK).body(EOTConstant.SUPERADMIN_DELETE_SUCCESSFULLY);
		}catch(EotException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String(e.getMessage()));
		}
		
		
	}
	
	
}
