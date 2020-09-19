package org.foodtruckService.info.service;

import org.foodtruckService.info.model.DistanceUnit;

import java.util.*;
import java.lang.*;
import java.io.*;

class GeoDesicDistanceCalculator{

    private static GeoDesicDistanceCalculator geoDesicDistanceCalculator;
    private GeoDesicDistanceCalculator(){
    }
    public static GeoDesicDistanceCalculator getInstance(){
        if(geoDesicDistanceCalculator == null){
            synchronized (GeoDesicDistanceCalculator.class){
                if(geoDesicDistanceCalculator == null){
                    geoDesicDistanceCalculator = new GeoDesicDistanceCalculator();
                }
            }
        }
        return geoDesicDistanceCalculator;
    }

    static double distance(double lat1, double lon1, double lat2, double lon2, DistanceUnit unit) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        }
        else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            if (unit.equals(DistanceUnit.KILOMETERS)) {
                dist = dist * 1.609344;
            } else if (unit.equals(DistanceUnit.NAUTICAL_MILES)) {
                dist = dist * 0.8684;
            }
            return (dist);
        }
    }
}