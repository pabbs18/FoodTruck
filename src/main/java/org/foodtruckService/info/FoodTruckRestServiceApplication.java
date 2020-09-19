package org.foodtruckService.info;

import org.foodtruckService.info.dataAccess.FoodTruckAccessor;
import org.foodtruckService.info.service.FoodTruckClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FoodTruckRestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodTruckRestServiceApplication.class, args);
        FoodTruckClient.getInstance().accessApiData();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		FoodTruckAccessor.getInstance().getSizeOfMap();
	}

}
