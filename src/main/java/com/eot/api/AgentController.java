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
import com.eot.domain.model.Distributer;
import com.eot.domain.model.MGurush;
import com.eot.domain.services.AgentService;
import com.eot.domain.services.MgurushService;
import com.eot.util.EotException;

@Controller
public class AgentController {
	
	@Autowired
	MgurushService mgurushService;
	
	@Autowired
	AgentService agentService;
	
	
	@RequestMapping(value = "/api/agent/{userId}",method = RequestMethod.POST)
	public ResponseEntity<Object> addAgent(@PathVariable("userId") String userId, @RequestBody Agent agent){
	
		try {
			MGurush mgurush = mgurushService.findMgurushByUserId(userId);
			
			if(mgurush != null) {
				if(mgurush.isActive() && mgurush.isAccountEnabled()) {
					
					agent.setCreatedBy(userId);
					agentService.saveOrUpdate(agent);
					
					return ResponseEntity.status(HttpStatus.OK).body(agent);

				}else {
					throw new EotException("Mgurush is not yet login ");
				}
				
			}else {
				throw new EotException("Mgurush does not exits");
			}
		} catch (EotException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
		
	}

}
