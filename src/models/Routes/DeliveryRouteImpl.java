package models.Routes;

import models.Location;
import models.contracts.DeliveryRoute;
import utils.DistanceCalculator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DeliveryRouteImpl implements DeliveryRoute {

    private final String id;
    private  final List<RouteCheckpoint> checkpoints;

    public DeliveryRouteImpl(Location startLocation, Location endLocation, LocalDateTime departureTime){
        this.id = generateUniqueRouteID();
        this.checkpoints = new ArrayList<>();
        addCheckpoint(startLocation, departureTime, true);
        //TODO will add local date time.now for testing, needs to be changed later
        addCheckpoint(endLocation, LocalDateTime.now(), true);
    }

    private String generateUniqueRouteID(){
        return UUID.randomUUID().toString().substring(0, 8);
    }

    @Override
    public String getRouteID() {
        return id;
    }

    public void addCheckpoint(Location location, LocalDateTime time, boolean isAvailable) {
        checkpoints.add(new RouteCheckpoint(location, time, isAvailable));
    }

    @Override
    public List<String> getLocations() {
        return checkpoints.stream()
                .map(cp ->cp.location().toString())
                .toList();
    }

    @Override
    public LocalDateTime getDepartureTime() {
        return checkpoints.isEmpty() ? null : checkpoints.get(0).timestamp();
    }
        //TODO need to add logic for arrival time to be assigned to the package
    @Override
    public LocalDateTime getArrivalTime() {
        return checkpoints.isEmpty() ? null : checkpoints.get(0).timestamp();
    }

    @Override
    public int getTotalStops() {
        return checkpoints.size();
    }

    @Override
    public int getTotalDistance(){
        if (checkpoints.size() <2) return 0;

        Location[] locations;
        locations = checkpoints.stream()
                .map(RouteCheckpoint::location)
                .toArray(Location[]::new);

        return DistanceCalculator.calculateDistance(locations);

    }
    @Override
    public String toString(){
        return String.format("Route with id: %s%n And stops in %s " +
                "%n", getRouteID(), getLocations().toString());
    }
}
