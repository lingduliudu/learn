 package com.example.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.example.tool.PropertyTool;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@PropertySource({"classpath:application.properties"})
@ComponentScan({"com"})
@ImportResource(locations={"classpath:applicationContext.xml"})
public class SpringConfig
{


  @Bean(name={"sessionFactory"})
  public LocalSessionFactoryBean sessionFactory(DataSource dataSource)
  {
    LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
     sfb.setDataSource(dataSource);
    sfb.setPackagesToScan(new String[] { PropertyTool.getProperty("jdbc.scanPackages") == null ? "com.free.model" : PropertyTool.getProperty("jdbc.scanPackages") });
    Properties prop = new Properties();
    prop.setProperty("hibernate.dialect", PropertyTool.getProperty("jdbc.dialect"));
    prop.setProperty("hibernate.show_sql", PropertyTool.getProperty("jdbc.show_sql") == null ? "false" : PropertyTool.getProperty("jdbc.show_sql"));
     prop.setProperty("hibernate.current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
     sfb.setHibernateProperties(prop);
    return sfb;
  }
  @Bean({"dataSource"})
  public ComboPooledDataSource dataSource() {
    ComboPooledDataSource bds = new ComboPooledDataSource();
    bds.setUser(PropertyTool.getProperty("jdbc.username"));
    try {
     bds.setDriverClass(PropertyTool.getProperty("jdbc.driverClassName"));
    } catch (PropertyVetoException e) {
      e.printStackTrace();
    }
   bds.setPassword(PropertyTool.getProperty("jdbc.password"));
    bds.setJdbcUrl(PropertyTool.getProperty("jdbc.url"));
    bds.setInitialPoolSize(10);
    return bds; } 
  @Bean(name={"transactionManager"})
  @Autowired
  @Required
  public HibernateTransactionManager transactionManager(SessionFactory s) { 
	  HibernateTransactionManager txManager = new HibernateTransactionManager();
	  txManager.setSessionFactory(s);
	  return txManager;
  }
}
