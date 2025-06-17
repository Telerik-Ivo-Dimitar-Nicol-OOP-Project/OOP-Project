package models.vehicles;

import models.VehicleName;

public class Man extends Vehicle {
    public Man(int id, VehicleName vehicleName){
        super(id, vehicleName);
    }

    @Override
    public VehicleName getName() {
        return this.name;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public int getRange() {
        return this.range;
    }
}
