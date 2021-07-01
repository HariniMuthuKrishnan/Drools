package com.drools.controller;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.drools.model.Order;

@RestController
public class CardController {

	public KieContainer kieContainer;
	
	@Autowired
	public CardController(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
	}

	@PostMapping("/order")
	public Order OrderNow(@RequestBody Order order) 
	{
		KieSession kieSession = kieContainer.newKieSession("rulesSession");
		System.out.println("Session Created" + order.toString());
		kieSession.insert(order);
		kieSession.fireAllRules();
		kieSession.dispose();
		return order;
	}

}
