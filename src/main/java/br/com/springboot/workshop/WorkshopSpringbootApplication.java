package br.com.springboot.workshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@RequestMapping("/hello-world")
public class WorkshopSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkshopSpringbootApplication.class, args);
	}
	
	@GetMapping
	public String HelloWorld () {
		return "Hello World!";
	}
}
