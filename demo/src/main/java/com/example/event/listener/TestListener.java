package com.example.event.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.example.event.TestEvent;

@Component
public class TestListener implements ApplicationListener<TestEvent> {

	@Override
	public void onApplicationEvent(TestEvent ae) {
		
	}



}
