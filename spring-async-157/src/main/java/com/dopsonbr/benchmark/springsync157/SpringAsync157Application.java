package com.dopsonbr.benchmark.springsync157;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringAsync157Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringAsync157Application.class, args);
	}
}
