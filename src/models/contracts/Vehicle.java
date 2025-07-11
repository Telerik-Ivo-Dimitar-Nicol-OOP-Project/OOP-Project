package models.contracts;

import models.VehicleName;

public interface Vehicle extends Identifiable{
    VehicleName getName();
    int getCapacity();
    int getRange();
    boolean isFree();
    void setFree(boolean isFree);

}
