package com.lctech.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = { "classpath:closetime.properties" })
public class CronConfig {
	 @Value("#{'${plan}'.split(',')}")
	 private List<String> schedules;

     @Bean
     public List<String> schedules() {
         return this.schedules;
     }

     public List<String> getSchedules() {
         return schedules;
     }

     public void setSchedules(List<String> schedules) {
         this.schedules = schedules;
     }
}
