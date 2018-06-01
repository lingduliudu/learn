/*    */ package com.example.dao.impl;
/*    */ 
/*    */ import com.example.dao.ICityDao;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.SessionFactory;
/*    */ import org.hibernate.transform.Transformers;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CityDaoImpl
/*    */   implements ICityDao
/*    */ {
/*    */   private SessionFactory sessionFactory;
/*    */ 
/*    */   @Autowired
/*    */   public void setSessionFactory(SessionFactory sessionFactory)
/*    */   {
/* 23 */     this.sessionFactory = sessionFactory;
/*    */   }
/*    */ 
/*    */   public List<Map> getCitys()
/*    */   {
/* 29 */     Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select * from city");
/* 30 */     query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
/* 31 */     return query.list();
/*    */   }
/*    */ }

/* Location:           C:\Users\ToT\Desktop\WEB-INF\classes\
 * Qualified Name:     com.example.dao.impl.CityDaoImpl
 * JD-Core Version:    0.6.1
 */