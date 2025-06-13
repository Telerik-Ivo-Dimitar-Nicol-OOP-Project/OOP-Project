package models.contracts;

import models.Location;

public interface Package extends Identifiable {
    Location getStartLocation();
    Location getEndLocation();
    double getWeight();
    String getContactInformation();
}
