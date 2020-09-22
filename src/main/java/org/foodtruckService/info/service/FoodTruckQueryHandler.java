package org.foodtruckService.info.service;

import org.foodtruckService.info.model.DistanceUnit;
import org.foodtruckService.info.model.FoodTruck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FoodTruckQueryHandler {

    @Autowired
    private FoodTruckAccessor foodTruckAccessor;
    @Autowired
    private GeoDesicDistanceCalculator geoDesicDistanceCalculator;

    public List<FoodTruck> getFoodTrucksByQuery(String userGivenStatusString, String userGivenLatitude,
                                                String userGivenLongitude, String userGivenRadius,
                                                 String userGivenRadiusUnitString){
        List<FoodTruck> foodTruckListByQuery;
        String defaultStatus = "ALL";

        if(userGivenStatusString != null){
            defaultStatus = userGivenStatusString;
        }
        foodTruckListByQuery = foodTruckAccessor.getFoodTrucksByStatus(defaultStatus);
        if(userGivenLatitude != null && userGivenLongitude != null && userGivenRadius != null && userGivenRadiusUnitString != null){
            try {
                double latitudeUnit = Double.parseDouble(userGivenLatitude);
                double longitudeUnit = Double.parseDouble(userGivenLongitude);
                double radius = Double.parseDouble(userGivenRadius);
                DistanceUnit radiiUnit = DistanceUnit.getDistanceUnitFromStringValue(userGivenRadiusUnitString);
                foodTruckListByQuery = getFoodTrucksInsideTheCircle(foodTruckListByQuery, latitudeUnit, longitudeUnit, radius, radiiUnit);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return foodTruckListByQuery;
    }

    public List<FoodTruck> getFoodTrucksInsideTheCircle(
            List<FoodTruck> foodTruckList,
            Double userGivenLatitude,
            Double userGivenLongitude,
            Double userGivenRadius,
            DistanceUnit radiusUnit
    ){
        List<FoodTruck> foodTruckListInsideCircle = new ArrayList<>();

       for (FoodTruck foodTruck: foodTruckList){

           double distance = geoDesicDistanceCalculator.distance(foodTruck.getLatitude(),
                   foodTruck.getLongitude(),userGivenLatitude,userGivenLongitude,radiusUnit);

           if(distance < userGivenRadius){
               foodTruckListInsideCircle.add(foodTruck);
           }
       }
       return foodTruckListInsideCircle;
    }
}
