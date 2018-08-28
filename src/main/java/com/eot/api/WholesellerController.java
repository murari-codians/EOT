package com.eot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eot.domain.model.Distributer;
import com.eot.domain.model.Wholeseller;
import com.eot.domain.services.DistributerService;
import com.eot.domain.services.WholesellerService;
import com.eot.util.EotException;

@Controller
public class WholesellerController {

	@Autowired
	WholesellerService wholesellerService;

	@Autowired
	DistributerService distributerService;

	@RequestMapping(value = "/api/wholeseller/{userId}", method = RequestMethod.POST)
	public ResponseEntity<Object> addWholeseller(@PathVariable("userId") String userId,
			@RequestBody Wholeseller wholeseller) {

		try {
			Distributer distributer = distributerService.findDistributerByUserId(userId);
			if (distributer != null) {
				if (distributer.isActive() && distributer.isAccountEnabled()) {

					wholeseller.setDistributerId(userId);
					wholesellerService.saveOrUpdate(wholeseller);

					return ResponseEntity.status(HttpStatus.OK).body(wholeseller);

				} else {
					throw new EotException("Distributer is not yet login ");
				}

			} else {
				throw new EotException("Distributer does not exits");
			}
		} catch (EotException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

		}

	}

}
