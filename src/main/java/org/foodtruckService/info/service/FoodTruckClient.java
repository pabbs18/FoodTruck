package org.foodtruckService.info.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.foodtruckService.info.model.FoodTruck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Service
public class FoodTruckClient  {
    @Autowired
    private FoodTruckAccessor foodTruckAccessor;

    private static final String DATASF_URL= "https://data.sfgov.org/resource/rqzj-sfat.json";
    private static boolean initialized = false;

    public String accessApiData(){
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
                   foodTruckAccessor.addFoodTruck(foodTruck);
               }
               initialized = true;
           }catch(java.net.UnknownHostException uhe){
               uhe.printStackTrace();
               System.out.println("Probably Unable to connect to the API Data Source: TRY AGAIN");
           } catch (JsonParseException jpe) {
              jpe.printStackTrace();
               System.out.println("Error in parsing the Json file");
           } catch (MalformedURLException mue) {
               System.out.println("Error in URL/ URL format ");
               mue.printStackTrace();
           } catch (JsonMappingException jme) {
               jme.printStackTrace();
           } catch (IOException ioe) {
               ioe.printStackTrace();
           }
       }
       if(initialized){
           return "Database successfully populated";
       }
       return "Database populating unsuccessful";
    }

}
