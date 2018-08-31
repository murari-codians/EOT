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
import com.eot.domain.model.Agent;
import com.eot.domain.model.Customer;
import com.eot.domain.services.AgentService;
import com.eot.domain.services.CustomerService;
import com.eot.util.EotException;

@Controller
public class CustomerController {

	@Autowired
	AgentService agentService;

	@Autowired
	CustomerService customerService;

	@RequestMapping(value = "/api/customer/{agentId}", method = RequestMethod.POST)
	public ResponseEntity<Object> createCustomer(@PathVariable("agentId") String agentId,
			@RequestBody Customer customer) {

		try {
			Agent agent = agentService.findAgentByUserId(agentId);
			if (agent != null) {

				if (agent.isActive() && agent.isAccountEnabled()) {
					customer.setAgentId(agent.getUserId());
					customerService.saveOrUpadte(customer);

					return ResponseEntity.status(HttpStatus.OK).body(customer);
				} else {
					throw new EotException("Agent is not logined");

				}
			} else {
				throw new EotException(EOTConstant.AGENT_DOESNOT_EXISTS);
			}
		} catch (EotException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String(e.getMessage()));

		}

	}

	@RequestMapping(value = "/api/agent/{agentId}/customer/{customerId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteCustomer(@PathVariable("agentId") String agentId,
			@PathVariable("customerId") String customerId) {

		try {
			Agent agent = agentService.findAgentByUserId(agentId);
			if (agent != null) {
				if (agent.isActive() && agent.isAccountEnabled()) {
					customerService.deleteCustomer(customerId);
					return ResponseEntity.status(HttpStatus.OK).body("Customer deleted sucessfully");

				} else {
					throw new EotException("Agent is not logined");

				}
			} else {
				throw new EotException(EOTConstant.AGENT_DOESNOT_EXISTS);
			}
		} catch (EotException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String(e.getMessage()));
		}
	}

	@RequestMapping(value = "/api/agent/{agentId}/customer/{customerId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateCustomer(@PathVariable("agentId") String agentId,
			@PathVariable("customerId") String customerId, @RequestBody Customer customer) {

		try {
			Agent agent = agentService.findAgentByUserId(agentId);
			if (agent != null) {
				if (agent.isActive() && agent.isAccountEnabled()) {
					customer.setCustomerId(customerId);
					customerService.updateCustomer(customer);

					return ResponseEntity.status(HttpStatus.OK).body("Customer updated sucessfully");

				} else {
					throw new EotException("Agent is Not logined");

				}
			} else {
				throw new EotException(EOTConstant.AGENT_DOESNOT_EXISTS);
			}
		} catch (EotException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String(e.getMessage()));
		}
	}

	@RequestMapping(value = "/api/agent/{agentId}/customers", method = RequestMethod.GET)
	public ResponseEntity<Object> getAll(@PathVariable("agentId") String agentId) {
		try {
			Agent agent = agentService.findAgentByUserId(agentId);
			if (agent != null) {
				if (agent.isActive() && agent.isAccountEnabled()) {
					List<Customer> customer = customerService.findAll();
					return ResponseEntity.status(HttpStatus.OK).body(customer);
				} else {
					throw new EotException("Agent is Not logined");

				}
			} else {
				throw new EotException(EOTConstant.AGENT_DOESNOT_EXISTS);
			}
		} catch (EotException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String(e.getMessage()));
		}
	}
}
