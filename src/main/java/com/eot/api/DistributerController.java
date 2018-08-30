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
import com.eot.domain.model.Distributer;
import com.eot.domain.model.Entiti;
import com.eot.domain.services.DistributerService;
import com.eot.domain.services.EntitiService;
import com.eot.util.EotException;

@Controller
public class DistributerController {

	@Autowired
	DistributerService distributerService;

	@RequestMapping(value = "api/distributer/{userId}", method = RequestMethod.POST)
	public ResponseEntity<Object> addDistributer(@PathVariable("userId") String userId,
			@RequestBody Distributer distributer) {

		try {

			distributerService.saveOrUpdate(userId, distributer);

			return ResponseEntity.status(HttpStatus.OK).body(distributer);


		} catch (EotException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

		}

	}

}
