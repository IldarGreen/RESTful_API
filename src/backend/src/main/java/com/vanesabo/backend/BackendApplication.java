package com.vanesabo.backend;

import com.vanesabo.backend.repository.AddressRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@OpenAPIDefinition(info = @Info(title = "Vanesabo RESTful API",
//								version = "1.0",
//								description = "Project backend 2"))
public class BackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

//	@Autowired
//	AddressRepository addressRepository;
//
//	@Bean
////	@ConditionalOnProperty(prefix = "app", name = "db.init.enabled", havingValue = "true")
//	public CommandLineRunner commandLineRunner() {
//		return args -> {
//			System.out.println("Running.....");
////
////			AddressEntity address1 = new AddressEntity("Франция", "Леон", "Вьен");
////			AddressEntity address2 = new AddressEntity("Россия", "Сакт-Петербург", "Лунная");
////
////			addressRepository.saveAll(List.of(address1, address2));
//		};
//	}

}
