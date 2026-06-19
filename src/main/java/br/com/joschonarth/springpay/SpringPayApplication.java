package br.com.joschonarth.springpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SpringPayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringPayApplication.class, args);
	}

}
