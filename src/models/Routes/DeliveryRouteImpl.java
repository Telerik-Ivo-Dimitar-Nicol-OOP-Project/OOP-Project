package models.Routes;

import models.Location;
import models.contracts.DeliveryRoute;
import models.contracts.Package;
import models.contracts.Vehicle;
import utils.DistanceCalculator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static utils.ValidationHelpers.isValidLocation;

public class DeliveryRouteImpl implements DeliveryRoute {

    private final int id;
    private  final List<Location> checkpoints;
    private final LocalDateTime departureTime;
    private final LocalDateTime arrivalTime;
    private final List<Package> assignedPackages;
    private double weightOfAssignedPackages;
    private static final AtomicInteger idCounter = new AtomicInteger(1);
    private Vehicle assignedVehicle;



    public DeliveryRouteImpl(Location startLocation, Location endLocation){
        this.id = idCounter.getAndIncrement();
        this.checkpoints = new ArrayList<>();
        addCheckpoint(startLocation);
        addCheckpoint(endLocation);
        this.departureTime = LocalDateTime.now().plusDays(2);//TODO placeholder departure time. Could be made with some actual logic.
        this.arrivalTime = departureTime.plusDays(2);
        assignedPackages = new ArrayList<>();
        weightOfAssignedPackages = 0;
    }


    @Override
    public int getRouteID() {
        return id;
    }

    public void addCheckpoint(Location checkpoint) {
        if (!isValidLocation(checkpoint.name())) {
            throw new IllegalArgumentException("Invalid location: " + checkpoint);
        }

        if (checkpoints.isEmpty()) {
            checkpoints.add(checkpoint);
            return;
        }

        if (checkpoints.contains(checkpoint)) {
            return;
        }

        Location start = checkpoints.get(0);
        Location end = checkpoints.get(checkpoints.size() - 1);

        int bestDistance = Integer.MAX_VALUE;
        int bestInsertIndex = 1;

        for (int i = 1; i < checkpoints.size(); i++) {
            List<Location> tempRoute = new ArrayList<>(checkpoints);
            tempRoute.add(i, checkpoint);

            int distance = DistanceCalculator.calculateDistance(tempRoute.toArray(new Location[0]));

            if (distance < bestDistance) {
                bestDistance = distance;
                bestInsertIndex = i;
            }
        }

        checkpoints.add(bestInsertIndex, checkpoint);
    }

    @Override
    public List<Location> getLocations() {
        return checkpoints.stream().toList();

    }

    @Override
    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    @Override
    public LocalDateTime getArrivalTime() {
        return departureTime;
    }

    @Override
    public int getTotalStops() {
        return checkpoints.size();
    }

    @Override
    public int getTotalDistance() {
        if (checkpoints.size() < 2) return 0;

        Location[] locations = checkpoints.toArray(new Location[0]);

        return DistanceCalculator.calculateDistance(locations);
    }

    @Override
    public void addPackageToRoute(Package packageToAdd) {
        packageToAdd.setAssignedToRoute(true);
        packageToAdd.setRouteIdToWhichPackageIsAssigned(this.getRouteID());
        weightOfAssignedPackages += packageToAdd.getWeight();
        assignedPackages.add(packageToAdd);
    }

    @Override
    public void removePackageFromRoute(int packageID) {
        for (int i = 0; i < assignedPackages.size(); i++) {
            if (assignedPackages.get(i).getId() == packageID){
                assignedPackages.get(i).setAssignedToRoute(false);
                assignedPackages.get(i).setDelivered();
                weightOfAssignedPackages -= assignedPackages.get(i).getWeight();
                assignedPackages.remove(i);
                return;
            }

        }
        throw new IllegalArgumentException(String.format("No package with id: %d is assigned to route %s", packageID, getRouteID()));
    }

    @Override
    public Vehicle getAssignedVehicle() {
        return assignedVehicle;
    }

    @Override
    public void assignVehicle(Vehicle vehicle) {
        this.assignedVehicle = vehicle;
        vehicle.setFree(false);
    }

    @Override
    public String toString(){
        return String.format("Route with id: %s%n And stops in %s " +
                "%n", getRouteID(), getLocations().toString());
    }
}
