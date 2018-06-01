 package com.example.dao.impl;
 
 import com.example.dao.ICityDao;
 import java.util.List;
 import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
 import org.hibernate.transform.Transformers;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 @Repository
 public class CityDaoImpl
   implements ICityDao
 {
   private SessionFactory sessionFactory;
 
   @Autowired
   public void setSessionFactory(SessionFactory sessionFactory)
   {
   this.sessionFactory = sessionFactory;
   }
 
   public List<Map> getCitys()
   {
	   	Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select * from city");
    	query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
    	return query.list();
   }
 }
