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
import com.eot.domain.model.Retailer;
import com.eot.domain.model.Wholeseller;
import com.eot.domain.services.RetailerService;
import com.eot.domain.services.WholesellerService;
import com.eot.util.EotException;

@Controller
public class RetailerController {

	@Autowired
	WholesellerService wholesellerService;

	@Autowired
	RetailerService retailerService;

	@RequestMapping(value = "api/retailer/{userId}", method = RequestMethod.POST)
	public ResponseEntity<Object> addRetailer(@PathVariable("userId") String userId, @RequestBody Retailer retailer) {

		try {
			Wholeseller wholeseller = wholesellerService.findWholesellerByUserId(userId);
			if (wholeseller != null) {
				if (wholeseller.isActive() && wholeseller.isAccountEnabled()) {

					retailer.setWholesellerId(userId);
					retailerService.saveOrUpdate(retailer);

					return ResponseEntity.status(HttpStatus.OK).body(retailer);

				} else {
					throw new EotException(EOTConstant.WHOLESELLER_NOT_YET_LOGGED_IN);
				}

			} else {
				throw new EotException(EOTConstant.WHOLESELLER_DOESNT_EXISTS);
			}
		} catch (EotException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

		}

	}
}
