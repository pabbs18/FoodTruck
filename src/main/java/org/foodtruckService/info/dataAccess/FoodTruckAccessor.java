package org.foodtruckService.info.dataAccess;

import org.foodtruckService.info.model.FoodTruck;
import org.foodtruckService.info.model.FoodTruckStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FoodTruckAccessor {

    private static  Map<Integer, FoodTruck> foodTruckInfo_Map;
    private static Map<FoodTruckStatus, List<FoodTruck>> foodTruckListByStatus_Map;

    private FoodTruckAccessor(){
        foodTruckInfo_Map = new HashMap<>();
        foodTruckListByStatus_Map = new HashMap<>();
    }
    private static FoodTruckAccessor foodTruckAccessorInstance;

    public static FoodTruckAccessor getInstance(){
        if(foodTruckAccessorInstance == null){
            synchronized (FoodTruckAccessor.class){
                if(foodTruckAccessorInstance == null){
                    foodTruckAccessorInstance = new FoodTruckAccessor();
                }
            }
        }
        return foodTruckAccessorInstance;
    }

    //Test Method
    public void getSizeOfMap(){
        System.out.println(foodTruckInfo_Map.size());
    }

    public List<FoodTruck> getAllFoodTrucks(){

            /*foodTruckInfo_Map.entrySet().stream()
                .filter(e->e.getValue().getPermit().equalsIgnoreCase(FoodTruckStatus.APPROVED.getStringValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());*/

           List<FoodTruck> foodTruckList =  foodTruckInfo_Map
                                            .entrySet()
                                            .stream()
                                            .map(Map.Entry::getValue)
                                            .collect(Collectors.toList());
           System.out.println("Size of foodtruckmap: "+foodTruckInfo_Map.size());
           System.out.println("Size of list: "+foodTruckList.size());
           for(int i = 0; i<foodTruckList.size(); i++){
               String s =  foodTruckList.get(i).getApplicant();
               System.out.println(s);
           }






        //return  new ArrayList<FoodTruck>(foodTruckInfo_Map.values());
        return  foodTruckList;
    }

    public List<FoodTruck> getFoodTrucksByStatus(FoodTruckStatus status){
        if(status == FoodTruckStatus.ALL){
            return getAllFoodTrucks();
        }
        else if(status == FoodTruckStatus.NO_STATEMENT){
            return new ArrayList<FoodTruck>();
        }
        else{
            return foodTruckListByStatus_Map.get(status);
        }
    }

    public boolean doesFoodTruckExist(int id){
        return foodTruckInfo_Map.containsKey(id);
    }

    public FoodTruck getFoodTruck(int id){
        return foodTruckInfo_Map.get(id);
    }

    public void addFoodTruck(FoodTruck foodTruck){
        int objectId = foodTruck.getObjectid();
        System.out.println("Object ID: "+objectId);
        foodTruckInfo_Map.put(objectId, foodTruck);

        System.out.println(foodTruckInfo_Map.get(objectId).getObjectid()+", "+foodTruckInfo_Map.get(foodTruck.getObjectid()).getApplicant());
        addToFoodTruckListByStatus_Map(foodTruck);
    }

    public void updateFoodTruck(FoodTruck foodTruck){
        updateStatusInFoodTruckListByStatus_Map(foodTruck);
        foodTruckInfo_Map.put(foodTruck.getObjectid(), foodTruck);
    }

    public FoodTruck removeFoodTruck(int id){
        removeFoodTruckFromFoodTruckListByStatus_Map(foodTruckInfo_Map.get(id));
        return foodTruckInfo_Map.remove(id);
    }

    private void addToFoodTruckListByStatus_Map(FoodTruck foodTruck) {
        FoodTruckStatus foodTruckStatus = foodTruck.getStatusOfEnum();
        List<FoodTruck> foodTruckList;

        if(foodTruckListByStatus_Map.containsKey(foodTruckStatus)){
            foodTruckList = foodTruckListByStatus_Map.get(foodTruckStatus);
        }
        else{
            foodTruckList = new ArrayList<>();
        }
            foodTruckList.add(foodTruck);
        foodTruckListByStatus_Map.put(foodTruckStatus, foodTruckList);
    }


    private void updateStatusInFoodTruckListByStatus_Map(FoodTruck updatedFoodTruck) {
        if(foodTruckInfo_Map.containsKey(updatedFoodTruck.getObjectid())){
            FoodTruck existingFoodTruck = foodTruckInfo_Map.get(updatedFoodTruck.getObjectid());

            if(existingFoodTruck.getStatusOfEnum() != updatedFoodTruck.getStatusOfEnum()){
                removeFoodTruckFromFoodTruckListByStatus_Map(existingFoodTruck);
                addToFoodTruckListByStatus_Map(updatedFoodTruck);
            }
        }
    }

    private void removeFoodTruckFromFoodTruckListByStatus_Map(FoodTruck existingFoodTruck) {
        if(foodTruckInfo_Map.containsKey(existingFoodTruck.getObjectid())){
            if(foodTruckListByStatus_Map.containsKey(existingFoodTruck.getStatusOfEnum())){
                /*This part gives the list of existing food trucks based on status*/
                foodTruckListByStatus_Map.get(existingFoodTruck.getStatusOfEnum()).remove(existingFoodTruck);
            }
        }
    }


}
