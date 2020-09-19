package org.foodtruckService.info.controller;

import org.foodtruckService.info.dataAccess.FoodTruckAccessor;
import org.foodtruckService.info.model.FoodTruck;
import org.foodtruckService.info.service.FoodTruckQueryHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/foodTrucks")
public class FoodTruckController {

    @GetMapping(value = "/all", produces = "application/json")
    public List<FoodTruck> getFoodTrucks(){
        return FoodTruckAccessor.getInstance().getAllFoodTrucks();
    }

    @GetMapping(value = "/query", produces = "application/json")
    public List<FoodTruck> getFoodTrucksByQuery(
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "latitude", required = false) String latitude,
            @RequestParam(value = "longtitude", required = false) String longitude,
            @RequestParam(value = "radius", required = false) String radius,
            @RequestParam(value = "radius_unit", required = false) String radiusUnit
    ){
        return FoodTruckQueryHandler.getInstance().getFoodTrucksByQuery(status, latitude, longitude, radius, radiusUnit);
    }
}
