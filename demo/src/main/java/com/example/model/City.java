 package com.example.model;
 
 import javax.persistence.Entity;
 import javax.persistence.Table;
 
 @Entity
 @Table(name="city", schema="test")
 public class City
 {
   private String time;
   private String city;
   private String count;
 
   public String getTime()
   {
    return this.time;
   }
   public void setTime(String time) {
    this.time = time;
   }
   public String getCity() {
    return this.city;
   }
   public void setCity(String city) {
     this.city = city;
   }
   public String getCount() {
    return this.count;
   }
   public void setCount(String count) {
     this.count = count;
   }
 }
