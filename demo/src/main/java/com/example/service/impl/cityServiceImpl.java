/*    */ package com.example.service.impl;
/*    */ 
/*    */ import com.example.dao.ICityDao;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import javax.annotation.Resource;
/*    */ import javax.transaction.Transactional;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Transactional
/*    */ @Service
/*    */ public class cityServiceImpl
/*    */   implements ICityService
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private ICityDao cityDao;
/*    */ 
/*    */   @Transactional
/*    */   public List<Map> getCitys()
/*    */   {
/* 23 */     return this.cityDao.getCitys();
/*    */   }
/*    */ }

/* Location:           C:\Users\ToT\Desktop\WEB-INF\classes\
 * Qualified Name:     com.example.service.impl.cityServiceImpl
 * JD-Core Version:    0.6.1
 */