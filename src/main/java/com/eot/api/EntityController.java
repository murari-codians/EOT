package com.eot.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eot.core.EOTConstant;
import com.eot.domain.model.Entiti;
import com.eot.domain.model.SuperAdmin;
import com.eot.domain.services.EntitiService;
import com.eot.domain.services.SuperAdminService;
import com.eot.util.EotException;

@Controller
public class EntityController {

	@Autowired
	SuperAdminService superAdminService;

	@Autowired
	EntitiService entitiService;

	@RequestMapping(value = "/api/Entity/{userId}", method = RequestMethod.POST)
	public ResponseEntity<Object> createEntiti(@PathVariable("userId") String userId, @RequestBody Entiti entiti) {

		try {
			SuperAdmin admin = superAdminService.findAdminByUserId(userId);
			if (admin != null) {

				if (admin.isActive() && admin.isAccountEnabled()) {
					entitiService.saveOrUpadte(entiti);

					return ResponseEntity.status(HttpStatus.OK).body(entiti);
				} else {
					throw new EotException("Superadmin is not logined");

				}
			} else {
				throw new EotException(EOTConstant.SUPERADMIN_DOESNOT_EXISTS);
			}
		} catch (EotException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String(e.getMessage()));

		}

	}

	@RequestMapping(value = "/api/admin/{userId}/Entity/{entityId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteEntiti(@PathVariable("userId") String userId,
			@PathVariable("entityId") String entityId) {

		try {
			SuperAdmin admin = superAdminService.findAdminByUserId(userId);
			if (admin != null) {
				if (admin.isActive() && admin.isAccountEnabled()) {
					entitiService.deleteEntiti(entityId);
					return ResponseEntity.status(HttpStatus.OK).body("Entity deleted sucessfully");

				} else {
					throw new EotException("Superadmin is not logined");

				}
			} else {
				throw new EotException(EOTConstant.SUPERADMIN_DOESNOT_EXISTS);
			}
		} catch (EotException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String(e.getMessage()));
		}
	}

	@RequestMapping(value = "/api/admin/{userId}/Entity/{entityId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateEntiti(@PathVariable("userId") String userId,
			@PathVariable("entityId") String entityId, @RequestBody Entiti entiti) {

		try {
			SuperAdmin admin = superAdminService.findAdminByUserId(userId);
			if (admin != null) {
				if (admin.isActive() && admin.isAccountEnabled()) {
					entiti.setUserId(entityId);
					entitiService.updateEntiti(entiti);

					return ResponseEntity.status(HttpStatus.OK).body("Entity updated sucessfully");

				} else {
					throw new EotException("Superadmin is Not logined");

				}
			} else {
				throw new EotException(EOTConstant.SUPERADMIN_DOESNOT_EXISTS);
			}
		} catch (EotException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String(e.getMessage()));
		}
	}

	@RequestMapping(value = "/api/admin/{userId}/entities", method = RequestMethod.GET)
	public ResponseEntity<Object> getAll(@PathVariable("userId") String userId) {
		try {
			SuperAdmin admin = superAdminService.findAdminByUserId(userId);
			if (admin != null) {
				if (admin.isActive() && admin.isAccountEnabled()) {
					List<Entiti> gurush = entitiService.findAll();
					return ResponseEntity.status(HttpStatus.OK).body(gurush);
				} else {
					throw new EotException("Superadmin is disabled");

				}
			} else {
				throw new EotException(EOTConstant.SUPERADMIN_DOESNOT_EXISTS);
			}
		} catch (EotException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String(e.getMessage()));
		}
	}

}
