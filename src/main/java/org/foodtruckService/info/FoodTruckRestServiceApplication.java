package org.foodtruckService.info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages="org.foodtruckService.info.dataAccess")
public class FoodTruckRestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodTruckRestServiceApplication.class, args);
	}

}
