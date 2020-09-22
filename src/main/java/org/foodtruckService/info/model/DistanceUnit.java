package org.foodtruckService.info.model;

public enum DistanceUnit {
    NO_STATEMENT(""),
    KILOMETERS("km"),
    NAUTICAL_MILES("nmi"),
    MILES("mi")
    ;


    private final String stringValue;

    DistanceUnit(String stringValue) {

        this.stringValue = stringValue;
    }

    public String getStrValue() {
        return stringValue;
    }

    public static DistanceUnit getDistanceUnitFromStringValue(String inputStringValue) {
        if (inputStringValue == null) {
            return NO_STATEMENT;

        } else if (inputStringValue.equalsIgnoreCase(KILOMETERS.stringValue)) {
            return KILOMETERS;
        } else if (inputStringValue.equalsIgnoreCase(NAUTICAL_MILES.stringValue)) {
            return NAUTICAL_MILES;
        } else if (inputStringValue.equalsIgnoreCase(MILES.stringValue)) {
            return MILES;
        }
        return NO_STATEMENT;
    }

}
