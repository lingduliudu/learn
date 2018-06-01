/*    */ package com.example.demo;
/*    */ 
/*    */ import org.springframework.boot.SpringApplication;
/*    */ import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
/*    */ import org.springframework.boot.autoconfigure.SpringBootApplication;
/*    */ import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
/*    */ import org.springframework.boot.builder.SpringApplicationBuilder;
/*    */ import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/*    */ 
/*    */ @SpringBootApplication(scanBasePackages={"com"})
/*    */ @EnableAutoConfiguration(exclude={HibernateJpaAutoConfiguration.class})
/*    */ public class DemoApplication extends SpringBootServletInitializer
/*    */ {
/*    */   protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
/*    */   {
/* 15 */     return builder.sources(new Class[] { DemoApplication.class });
/*    */   }
/*    */   public static void main(String[] args) {
/* 18 */     SpringApplication.run(DemoApplication.class, args);
/*    */   }
/*    */ }

/* Location:           C:\Users\ToT\Desktop\WEB-INF\classes\
 * Qualified Name:     com.example.demo.DemoApplication
 * JD-Core Version:    0.6.1
 */