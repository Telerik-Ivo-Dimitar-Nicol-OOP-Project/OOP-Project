package models.contracts;

import models.Location;

public interface Package extends Identifiable, Printable {
    Location getStartLocation();

    Location getEndLocation();

    double getWeight();

    String getContactInformation();

    boolean isAssignedToRoute();

    void setAssignedToRoute(boolean assignedToRoute);

    void setDelivered();

    boolean isDelivered();

    String getRouteIdToWhichPackageIsAssigned();

    void setRouteIdToWhichPackageIsAssigned(int routeID);
}
