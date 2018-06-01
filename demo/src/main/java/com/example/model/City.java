/*    */ package com.example.model;
/*    */ 
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.Table;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="city", schema="test")
/*    */ public class City
/*    */ {
/*    */   private String time;
/*    */   private String city;
/*    */   private String count;
/*    */ 
/*    */   public String getTime()
/*    */   {
/* 13 */     return this.time;
/*    */   }
/*    */   public void setTime(String time) {
/* 16 */     this.time = time;
/*    */   }
/*    */   public String getCity() {
/* 19 */     return this.city;
/*    */   }
/*    */   public void setCity(String city) {
/* 22 */     this.city = city;
/*    */   }
/*    */   public String getCount() {
/* 25 */     return this.count;
/*    */   }
/*    */   public void setCount(String count) {
/* 28 */     this.count = count;
/*    */   }
/*    */ }

/* Location:           C:\Users\ToT\Desktop\WEB-INF\classes\
 * Qualified Name:     com.example.model.City
 * JD-Core Version:    0.6.1
 */