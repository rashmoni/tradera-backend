package com.novare.traderabackend;

import com.novare.traderabackend.Repository.TraderRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = TraderRepo.class)
public class TraderaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TraderaBackendApplication.class, args);
	}
}
