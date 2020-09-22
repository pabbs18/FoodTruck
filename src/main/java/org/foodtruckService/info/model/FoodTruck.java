package org.foodtruckService.info.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "FoodTruck")
public class FoodTruck {
    @Id
    @Column(name = "objectid")
    private int objectid;
    @Column(name = "address", length = 1000)
    private String address;
    @Column(name = "locationdescription", length = 1000)
    private String locationdescription;
    @Column(name = "applicant", length = 1000)
    private String applicant;
    @Column(name = "cnn")
    private int cnn;
    @Column(name = "dayshours", length = 1000)
    private String dayshours;
    @Column(name = "facilitytype", length = 1000)
    private String facilitytype;
    @Column(name = "fooditems", length = 1000)
    private String fooditems;
    @Column(name = "latitude")
    private double latitude;
    @Column(name = "longitude")
    private double longitude;
    @Column(name = "permit", length = 1000)
    private String permit;
    @Column(name = "schedule", length = 1000)
    private String schedule;
    @Column(name = "status")
    private String status;

    public FoodTruck() {
        super();
    }

    public int getObjectid() {
        return objectid;
    }

    public void setObjectid(int objectid) {
        this.objectid = objectid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocationdescription() {
        return locationdescription;
    }

    public void setLocationdescription(String locationdescription) {
        this.locationdescription = locationdescription;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public int getCnn() {
        return cnn;
    }

    public void setCnn(int cnn) {
        this.cnn = cnn;
    }

    public String getDayshours() {
        return dayshours;
    }

    public void setDayshours(String dayshours) {
        this.dayshours = dayshours;
    }

    public String getFacilitytype() {
        return facilitytype;
    }

    public void setFacilitytype(String facilitytype) {
        this.facilitytype = facilitytype;
    }

    public String getFooditems() {
        return fooditems;
    }

    public void setFooditems(String fooditems) {
        this.fooditems = fooditems;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPermit() {
        return permit;
    }

    public void setPermit(String permit) {
        this.permit = permit;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
