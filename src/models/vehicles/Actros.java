package models.vehicles;

import models.VehicleName;

public class Actros extends Vehicle{
    public Actros(int id, VehicleName vehicleName){
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
