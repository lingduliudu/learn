package com.zhiwang.controller;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhiwang.jms.IntegrationMessage;
import com.zhiwang.jms.MessageService;

@RestController
public class JMSController {
	
    @Autowired
    @Qualifier("apiQueueDestination")
    private Destination apiQueueDestination;
    @Autowired
    private MessageService messageService;
	
	@RequestMapping(value = "/api/message/{content}", method = RequestMethod.GET)
	public String publishMessgae(@PathVariable("content") String content){
		IntegrationMessage integrationMessage = new IntegrationMessage();
		integrationMessage.setModelName("API_MODEL");
		integrationMessage.setModelVersion("v1.0");
		integrationMessage.setSource("producer");
		integrationMessage.setTarget("consumer");
		integrationMessage.setMessageKey("ddddddd");
		integrationMessage.setMessageBody(content);
		boolean flag = messageService.publish(apiQueueDestination, integrationMessage);
		
		return flag + "";
	}

}
