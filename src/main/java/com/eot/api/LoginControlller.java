package com.eot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eot.domain.model.Login;
import com.eot.domain.services.LoginService;
import com.eot.util.EotException;

@Controller
public class LoginControlller {

	@Autowired
	LoginService loginSrvice;

	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
	public ResponseEntity<Object> userLogin(@RequestBody Login login) {

		try {
			loginSrvice.loginUser(login);

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("login sucessfully");
		} catch (EotException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

}
