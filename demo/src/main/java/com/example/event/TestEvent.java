package com.example.event;

import org.springframework.context.ApplicationEvent;

public class TestEvent  extends ApplicationEvent{
	private static final long serialVersionUID = 4291447692726317058L;
	
	private String text;

	public TestEvent(Object source) {
		super(source);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	

}
