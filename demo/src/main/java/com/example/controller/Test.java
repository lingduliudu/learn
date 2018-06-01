 package com.example.controller;
 
 import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.impl.ICityService;
import com.example.tool.PropertyTool;
 
 @RestController
 public class Test
 {
 
   @Resource
   private ICityService cityService;
 
   @GetMapping({"/test/test1"})
   public String test1()
   {
     System.out.println(this.cityService.getCitys());
     System.out.println(PropertyTool.getProperty("abc"));
     System.out.println(PropertyTool.getProperty("jdbc.password"));
     return "123F";
   }
 }
