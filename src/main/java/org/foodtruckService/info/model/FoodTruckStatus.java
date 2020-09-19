package org.foodtruckService.info.model;

public enum FoodTruckStatus {

    NO_STATEMENT("NO STATEMENT"),
    REQUESTED("REQUESTED"),
    APPROVED("APPROVED"),
    INACTIVE("INACTIVE"),


    SUSPENDED("SUSPEND"),
    EXPIRED("EXPIRED"),
    ONHOLD("ONHOLD"),
    ALL("ALL")
    ;
    private String stringValue;

    FoodTruckStatus(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public static FoodTruckStatus getFoodTruckStatusFromStringValue(String inputStringValue){
        if(inputStringValue == null){
            return NO_STATEMENT;
        }
        else if(inputStringValue.equalsIgnoreCase(REQUESTED.stringValue)){
            return REQUESTED;
        }
        else if(inputStringValue.equalsIgnoreCase(APPROVED.stringValue)){
            return APPROVED;
        }
        else if(inputStringValue.equalsIgnoreCase(INACTIVE.stringValue)){
            return INACTIVE;
        }
        else if(inputStringValue.equalsIgnoreCase(SUSPENDED.stringValue)){
            return SUSPENDED;
        }
        else if(inputStringValue.equalsIgnoreCase(EXPIRED.stringValue)){
            return EXPIRED;
        }
        else if(inputStringValue.equalsIgnoreCase(ONHOLD.stringValue)){
            return ONHOLD;
        }
        else if(inputStringValue.equalsIgnoreCase(ALL.stringValue)){
            return ALL;
        }
        return NO_STATEMENT;
    }
}
