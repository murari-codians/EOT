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
	EntitiService mgurushService;

	@RequestMapping(value = "/api/Entity/{userId}", method = RequestMethod.POST)
	public ResponseEntity<Object> gurushCreate(@PathVariable("userId") String userId, @RequestBody Entiti mGurush) {

		try {
			SuperAdmin admin = superAdminService.findAdminByUserId(userId);
			if (admin != null) {

				if (admin.isActive() && admin.isAccountEnabled()) {
					mgurushService.saveOrUpadte(mGurush);

					return ResponseEntity.status(HttpStatus.OK).body(mGurush);
				} else {
					throw new EotException(EOTConstant.SUPERADMIN_DISABLED);
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
	public ResponseEntity<Object> deleteMgurush(@PathVariable("userId") String userId,
			@PathVariable("entityId") String entityId) {

		try {
			SuperAdmin admin = superAdminService.findAdminByUserId(userId);
			if (admin != null) {
				if (admin.isActive() && admin.isAccountEnabled()) {
					mgurushService.deleteMgurush(entityId);
					return ResponseEntity.status(HttpStatus.OK).body(EOTConstant.ENTITY_DELETED_SUCCESSFULLY);
				} else {
					throw new EotException(EOTConstant.SUPERADMIN_DISABLED);
				}
			} else {
				throw new EotException(EOTConstant.SUPERADMIN_DOESNOT_EXISTS);
			}
		} catch (EotException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String(e.getMessage()));
		}
	}

	@RequestMapping(value = "/api/admin/{userId}/Entity/{entityId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateMgurush(@PathVariable("userId") String userId,
			@PathVariable("entityId") String entityId, @RequestBody Entiti mGurush) {

		try {
			SuperAdmin admin = superAdminService.findAdminByUserId(userId);
			if (admin != null) {
				if (admin.isActive() && admin.isAccountEnabled()) {
					mGurush.setUserId(entityId);
					mgurushService.updateMgurush(mGurush);

					return ResponseEntity.status(HttpStatus.OK).body(EOTConstant.ENTITY_UPDATED_SUCCESSFULLY);
				} else {
					throw new EotException(EOTConstant.SUPERADMIN_DISABLED);
				}
			} else {
				throw new EotException(EOTConstant.SUPERADMIN_DOESNOT_EXISTS);
			}
		} catch (EotException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String(e.getMessage()));
		}
	}

	@RequestMapping(value = "/api/admin/{userId}/entity", method = RequestMethod.GET)
	public ResponseEntity<Object> getAll(@PathVariable("userId") String userId) {
		try {
			SuperAdmin admin = superAdminService.findAdminByUserId(userId);
			if (admin != null) {
				if (admin.isActive() && admin.isAccountEnabled()) {
					List<Entiti> gurush = mgurushService.findAll();
					return ResponseEntity.status(HttpStatus.OK).body(gurush);
				} else {
					throw new EotException(EOTConstant.SUPERADMIN_DISABLED);
				}
			} else {
				throw new EotException(EOTConstant.SUPERADMIN_DOESNOT_EXISTS);
			}
		} catch (EotException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String(e.getMessage()));
		}
	}

}
