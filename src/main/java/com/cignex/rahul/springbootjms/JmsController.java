package com.cignex.rahul.springbootjms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jms")
public class JmsController {

	@Autowired
	protected ApplicationContext app;

	@RequestMapping(method = RequestMethod.POST)
	public String sendMessage(@RequestBody Email email) {
		
		JmsTemplate jmsTemplate = app.getBean(JmsTemplate.class);
		System.out.println("Sending an email message.");
		try {
			jmsTemplate.convertAndSend("mailbox", email);
			return "Message sent Successfully";
		} catch (JmsException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

}
