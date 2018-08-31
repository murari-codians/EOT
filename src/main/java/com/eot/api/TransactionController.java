package com.eot.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eot.core.LoginTypes;
import com.eot.core.TransactionTypes;
import com.eot.domain.dao.LoginDao;
import com.eot.domain.model.Agent;
import com.eot.domain.model.Transaction;
import com.eot.domain.services.AgentService;
import com.eot.domain.services.TransactionService;
import com.eot.util.EotException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
public class TransactionController {

	@Autowired
	TransactionService transactionService;

	@Autowired
	AgentService agentService;

	@RequestMapping(value = "/api/transaction/{agentId}", method = RequestMethod.POST)
	public ResponseEntity<Object> deposite(@PathVariable("agentId") String agentId,
			@RequestBody Transaction transaction) {

		try {

			transactionService.deposite(agentId, transaction);
			return ResponseEntity.status(HttpStatus.OK).body(transaction);

		}

		catch (EotException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String(e.getMessage()));
		}

	}
	
	@RequestMapping(value = "/api/miniStatement/{agentId}", method = RequestMethod.POST)
	public ResponseEntity<Object> miniStatement(@PathVariable("agentId") String agentId,
			@RequestBody Transaction transaction) {

		try {

			transactionService.miniStatement(agentId, transaction);
			return ResponseEntity.status(HttpStatus.OK).body(transaction);

		}

		catch (EotException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String(e.getMessage()));
		}

	}

}
