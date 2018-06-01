 package com.example.controller;
 
 import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.event.TestEvent;
import com.example.service.impl.ICityService;
import com.example.tool.PropertyTool;
 
 @RestController
 public class Test
 {
 
   @Resource
   private ICityService cityService;
   @Resource
   private ApplicationContext applicationContext;
   
   @GetMapping({"/test/test1"})
   public String test1()
   {
	   /*
	    * 测试数据
	    * 
	    * 
	    *
	    */
	   TestEvent te = new TestEvent(new Object());
	   te.setText("-----------111------------");
	   applicationContext.publishEvent(te);
	   return "123F";
   }
 }
