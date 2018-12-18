package com.htl.cloudmemory;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author hetianlong
 * @since 2018.12.03
 *
 */
@EnableAutoConfiguration
@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.htl.cloudmemory.dao")
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		System.out.println("start success.");
	}
}
