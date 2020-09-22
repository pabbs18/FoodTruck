package org.foodtruckService.info.controller;

import org.foodtruckService.info.service.FoodTruckAccessor;
import org.foodtruckService.info.model.FoodTruck;
import org.foodtruckService.info.service.FoodTruckClient;
import org.foodtruckService.info.service.FoodTruckQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(value = "/foodTrucks")
public class FoodTruckController {
    @Autowired
    private FoodTruckClient foodTruckClient;
    @Autowired
    private FoodTruckQueryHandler foodTruckQueryHandler;
    @Autowired
    private FoodTruckAccessor foodTruckAccessor;

    @GetMapping(value = "/all", produces = "application/json")
    public List<FoodTruck> getFoodTrucks(){
        return foodTruckAccessor.getAllFoodTrucks();
    }

    @GetMapping(value = "/query", produces = "application/json")
    public List<FoodTruck> getFoodTrucksByQuery(
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "latitude", required = false) String latitude,
            @RequestParam(value = "longitude", required = false) String longitude,
            @RequestParam(value = "radius", required = false) String radius,
            @RequestParam(value = "radius_unit", required = false) String radiusUnit
    ){
        return foodTruckQueryHandler.getFoodTrucksByQuery(status, latitude, longitude, radius, radiusUnit);
    }

    @GetMapping(value = "/updateDB")
    public String updateDataBase(){
      return foodTruckClient.accessApiData();
    }
}
