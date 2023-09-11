package com.example.demo.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.context.support.RequestHandledEvent;
//Servlet에 있던 Listener~
//ServletContextEvent
//ServletContextAttributeEvent
//RequestContextEvent..


public class RequestHandledEventListener implements ApplicationListener<RequestHandledEvent>{

	@Override
	public void onApplicationEvent(RequestHandledEvent event) {
		System.out.println("[LISTENER] RequestHandledEventListener's onApplicationEvent " + event.getSource());
		
	}

}
