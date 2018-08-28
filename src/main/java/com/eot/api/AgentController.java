package com.eot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eot.domain.model.Agent;
import com.eot.domain.services.AgentService;
import com.eot.util.EotException;

@Controller
public class AgentController {

	@Autowired
	AgentService agentService;

	@RequestMapping(value = "/api/agent/{userId}", method = RequestMethod.POST)
	public ResponseEntity<Object> addAgent(@PathVariable("userId") String userId, @RequestBody Agent agent) {

		try {

			agentService.saveOrUpdate(userId, agent);
			return ResponseEntity.status(HttpStatus.OK).body(agent);

		} catch (EotException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String(e.getMessage()));
		}

	}
}
