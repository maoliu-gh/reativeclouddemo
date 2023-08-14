package com.example.reativeclouddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableBinding({Source.class, Sink.class})
public class ReativeclouddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReativeclouddemoApplication.class, args);
	
	
	}
}
