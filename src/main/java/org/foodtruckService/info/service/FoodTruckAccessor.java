package org.foodtruckService.info.service;

import org.foodtruckService.info.dataAccess.FoodTruckRepository;
import org.foodtruckService.info.model.FoodTruck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class FoodTruckAccessor {

    @Autowired
    private FoodTruckRepository foodTruckRepository;

    public List<FoodTruck> getAllFoodTrucks() {
        return foodTruckRepository.findAll();
    }

    public List<FoodTruck> getFoodTrucksByStatus(String status) {
        if (status.equals("ALL")) {
            return getAllFoodTrucks();
        } else if (status.equals("NO STATEMENT")) {
            return new ArrayList<FoodTruck>();
        } else {
            return foodTruckRepository.findByStatus(status);
        }
    }

    public FoodTruck getFoodTruck(int id) {
        return foodTruckRepository.findById(id);
    }

    public void addFoodTruck(FoodTruck foodTruck) {
        foodTruckRepository.save(foodTruck);
    }

   /* public void updateFoodTruck(int id){
        // update status and other fields for a given foodtruck
    }*/

    /*public FoodTruck removeFoodTruck(int id){
        // remove a food truck from the table
    }*/
}
