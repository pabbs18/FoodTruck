package org.foodtruckService.info.dataAccess;

import org.foodtruckService.info.model.FoodTruck;
//import org.foodtruckService.info.model.FoodTruckStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FoodTruckRepository extends JpaRepository<FoodTruck, Integer> {
    @Override
    List<FoodTruck> findAll();
    List<FoodTruck> findByStatus(String status);
    FoodTruck findById(int id);
}
