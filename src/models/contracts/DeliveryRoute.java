package models.contracts;

import models.Location;
import models.Routes.Checkpoint;
import java.time.LocalDateTime;
import java.util.List;

public interface DeliveryRoute extends Printable {

    public int getRouteID();
    public List<Location> getLocations();
    LocalDateTime getDepartureTime();
    LocalDateTime getArrivalTime();
    int getTotalStops();
    public int getTotalDistance();
    void addPackageToRoute(Package packageToAdd);
    void removePackageFromRoute(int packageID);
    Vehicle getAssignedVehicle();
    void assignVehicle(Vehicle vehicle);





    void calculateCheckpointsWithArrivalTimes(double averageSpeedKmh);
    List<Checkpoint> getCalculatedCheckpoints();
    boolean isSuitableForPackage(Package packageToAssign);
}
