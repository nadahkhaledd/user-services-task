package com.example.usm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.example.usm"})
@EnableCouchbaseRepositories(basePackages = {"com.example.usm"})
public class UserservicemanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserservicemanagementApplication.class, args);
	}


}
