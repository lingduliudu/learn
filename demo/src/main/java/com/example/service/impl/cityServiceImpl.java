 package com.example.service.impl;
 
 import com.example.dao.ICityDao;
 import java.util.List;
 import java.util.Map;
 import javax.annotation.Resource;
 import javax.transaction.Transactional;
 import org.springframework.stereotype.Service;
 
 @Transactional
 @Service
 public class cityServiceImpl
   implements ICityService
 {
 
   @Resource
   private ICityDao cityDao;
 
   @Transactional
   public List<Map> getCitys()
   {
	   return this.cityDao.getCitys();
   }
 }
