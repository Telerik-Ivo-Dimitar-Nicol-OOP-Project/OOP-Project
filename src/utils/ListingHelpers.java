package utils;

import core.commands.CommandConstants;
import models.PackageImpl;
import models.contracts.DeliveryRoute;
import models.contracts.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class ListingHelpers {
    public static String packagesToString(List<PackageImpl> packages){
        List<String> result = new ArrayList<>();
        for (PackageImpl pack : packages){
            result.add(pack.toString());
        }
        return String.join(CommandConstants.JOIN_DELIMITER + System.lineSeparator(), result).trim();
    }
    public static String routesToString(List<DeliveryRoute> routes){
        List<String> result = new ArrayList<>();
        for (DeliveryRoute route : routes){
            result.add(route.toString());
        }
        return String.join(CommandConstants.JOIN_DELIMITER + System.lineSeparator(), result).trim();
    }

    public static String vehiclesToString(List<Vehicle> vehicles) {
        StringBuilder sb = new StringBuilder("Truck list:\n");

        for (Vehicle v : vehicles) {
            models.vehicles.Vehicle truck = (models.vehicles.Vehicle) v;
            sb.append(String.format("[ID: %d] %s - %dkg / %dkm - %s\n",
                    truck.getId(),
                    truck.getName(),
                    truck.getCapacity(),
                    truck.getRange(),
                    truck.isFree() ? "Free" : "Busy"));
        }

        return sb.toString().trim();
    }



}
