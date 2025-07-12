package models.Routes;

import models.Location;

import java.time.LocalDateTime;

public class Checkpoint {

    private final Location location;
    private final int distanceFromPrevious;
    private final LocalDateTime arrivalTime;

    public Checkpoint(Location location, int distanceFromPrevious, LocalDateTime arrivalTime) {
        this.location = location;
        this.distanceFromPrevious = distanceFromPrevious;
        this.arrivalTime = arrivalTime;
    }

    public Location getLocation() {
        return location;
    }

    public int getDistanceFromPrevious() {
        return distanceFromPrevious;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    @Override
    public String toString() {
        return String.format("%s – %dkm – ETA: %s", location, distanceFromPrevious, arrivalTime);
    }
}
