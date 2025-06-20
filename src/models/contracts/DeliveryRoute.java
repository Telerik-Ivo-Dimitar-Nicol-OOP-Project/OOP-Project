package models.contracts;

import models.Location;

import java.time.LocalDateTime;
import java.util.List;

public interface DeliveryRoute {

    public String getRouteID();

    public void addCheckpoint(Location location, LocalDateTime time, boolean isAvailable);

    public List<String> getLocations();

    LocalDateTime getDepartureTime();

    LocalDateTime getArrivalTime();

    int totalStops();

    public int getTotalDistance();

}
