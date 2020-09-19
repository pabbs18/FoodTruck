package org.foodtruckService.info.service;

import org.foodtruckService.info.dataAccess.FoodTruckAccessor;
import org.foodtruckService.info.model.DistanceUnit;
import org.foodtruckService.info.model.FoodTruck;
import org.foodtruckService.info.model.FoodTruckStatus;

import java.util.ArrayList;
import java.util.List;

public class FoodTruckQueryHandler {
    private static FoodTruckQueryHandler foodTruckQueryHandlerInstance;
    private FoodTruckQueryHandler(){
    }
    public static FoodTruckQueryHandler getInstance(){
        if(foodTruckQueryHandlerInstance == null){
            synchronized (FoodTruckQueryHandler.class){
                if(foodTruckQueryHandlerInstance == null){
                    foodTruckQueryHandlerInstance = new FoodTruckQueryHandler();
                }
            }
        }
        return foodTruckQueryHandlerInstance;
    }

    public List<FoodTruck> getFoodTrucksByQuery(String userGivenStatusString, String userGivenLatitude,
                                                String userGivenLongitude, String userGivenRadius,
                                                 String userGivenRadiusUnitString){
        List<FoodTruck> foodTruckListByQuery;
        FoodTruckStatus defaultStatus = FoodTruckStatus.ALL;

        if(userGivenStatusString != null){
            defaultStatus = FoodTruckStatus.getFoodTruckStatusFromStringValue(userGivenStatusString);
        }
        foodTruckListByQuery = FoodTruckAccessor.getInstance().getFoodTrucksByStatus(defaultStatus);
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

           double distance = GeoDesicDistanceCalculator.getInstance().distance(foodTruck.getLatitude(),
                   foodTruck.getLongitude(),userGivenLatitude,userGivenLongitude,radiusUnit);

           if(distance < userGivenRadius){
               foodTruckListInsideCircle.add(foodTruck);
           }
       }
       return foodTruckListInsideCircle;
    }
}
