 package com.example.controller;
 
 import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.event.TestEvent;
import com.example.service.impl.ICityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
 
 @RestController
 @Api(description="测试类")
 public class Test
 {
 
   @Resource
   private ICityService cityService;
   @Resource
   private ApplicationContext applicationContext;
   
   @GetMapping({"/test/test1"})
   @ApiOperation(notes = "测试Swagger", value = "test")
  /* @ApiImplicitParams({
   @ApiImplicitParam(value="描述1231",name="param",dataType="String",paramType = "query"), 
   @ApiImplicitParam(value="描述121231",name="param2",dataType="String",paramType = "query")
   })*/
   public String test1(@ApiParam(required = true, name="输出") String param)
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
