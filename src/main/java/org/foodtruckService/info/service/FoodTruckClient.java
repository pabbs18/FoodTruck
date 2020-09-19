package org.foodtruckService.info.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.foodtruckService.info.dataAccess.FoodTruckAccessor;
import org.foodtruckService.info.model.FoodTruck;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class FoodTruckClient {

    private static final String DATASF_URL = "https://data.sfgov.org/resource/6a9r-agq8.json";




    private static boolean initialized = false;

    private FoodTruckClient(){
    }
    private static FoodTruckClient foodTruckClientInstance ;

    public static FoodTruckClient getInstance(){
        if(foodTruckClientInstance == null){
            synchronized (FoodTruckClient.class){
                if(foodTruckClientInstance == null){
                    foodTruckClientInstance = new FoodTruckClient();
                }
            }
        }
        return foodTruckClientInstance;
    }

    public void accessApiData(){
       if(!initialized){
           try {
               URL url = new URL(DATASF_URL);
               ObjectMapper objectMapper = new ObjectMapper();
              List<FoodTruck> foodTruckList = objectMapper.readValue(url, new TypeReference<List<FoodTruck>>() {
               });
               int count =0;// delete it later
               for(FoodTruck foodTruck: foodTruckList){
                   System.out.println(count++);//delete it later
                   System.out.println("CNN ID at api read: "+foodTruck.getCnn());
                   FoodTruckAccessor.getInstance().addFoodTruck(foodTruck);
               }
               initialized = true;
           } catch (JsonParseException e) {
               e.printStackTrace();
               System.out.println("Error in parsing th Json file");
           } catch (MalformedURLException e) {
               System.out.println("Error in URL/ URL format ");
               e.printStackTrace();
           } catch (JsonMappingException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
    }

}
