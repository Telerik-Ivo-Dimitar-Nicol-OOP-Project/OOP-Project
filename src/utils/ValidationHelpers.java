package utils;

import models.Location;
import models.Routes.DeliveryRouteImpl;
import models.contracts.DeliveryRoute;
import models.contracts.Package;

import java.util.List;

public class ValidationHelpers {
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments. Expected: %d, Received: %d";

    public static void validateValueInRange(double value, double min, double max, String errorMessage) {
        // Needs to check if value > min and if value < max
        if (value < min || value > max ){
            throw new IllegalArgumentException(errorMessage);
        }
    }
    public static void validateWeightIsCorrect(double value, double min, double max, String errorMessage) {
        // Needs to check if value > min and if value < max
        if (value < min || value > max ){
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validateStringLength(String stringToValidate, int minLength, int maxLength, String errorMessage) {
        validateValueInRange(stringToValidate.length(), minLength, maxLength, errorMessage);
    }

    public static void validateArgumentsCount(List<String> list, int expectedArgumentsCount) {
        if (list.size() < expectedArgumentsCount || list.size() > expectedArgumentsCount) {
            throw new IllegalArgumentException(String.format(INVALID_NUMBER_OF_ARGUMENTS, expectedArgumentsCount, list.size()));
        }
    }
    public static boolean validateSuitableRoute(Package packageToCheckRoutesFor, DeliveryRoute route){
        Location startLocation = packageToCheckRoutesFor.getStartLocation();
        Location endLocation = packageToCheckRoutesFor.getEndLocation();
        boolean hasSuitableStartLocation = false;
        boolean hasSuitableEndLocation = false;
        List<String> routeLocations = route.getLocations();
        int indexOfStarLocation = 0;
        for (int i = 0; i < routeLocations.size(); i++) {
            if (routeLocations.get(i).equals(startLocation.toString())){
                hasSuitableStartLocation = true;
                indexOfStarLocation = i;
                break;
            }
        }

        for (int i = indexOfStarLocation + 1; i < routeLocations.size(); i++) {
            if (routeLocations.get(i).equals(endLocation.toString())){
                hasSuitableEndLocation = true;
                break;
            }
        }
        if (hasSuitableStartLocation && hasSuitableEndLocation){
            return true;
        }
        return false;

    }

    public static boolean isValidLocation(String input){
        try{
            Location.valueOf(input.toUpperCase());
            return true;
        } catch (IllegalArgumentException e){
            return false;
        }
    }
}
