package com.yu.bt.mybt;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;



@SpringBootApplication
@EnableJpaAuditing
public class MybtApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybtApplication.class, args);
	}

}
