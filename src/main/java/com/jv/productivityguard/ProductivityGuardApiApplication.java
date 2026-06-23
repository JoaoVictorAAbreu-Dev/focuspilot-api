package com.jv.productivityguard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ProductivityGuardApiApplication {
  public static void main(String[] args) {
    SpringApplication.run(ProductivityGuardApiApplication.class, args);
  }
}
