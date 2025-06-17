package models.vehicles;

import models.VehicleName;

public class Scania extends Vehicle implements models.contracts.Vehicle {

    public Scania(int id, VehicleName vehicleName){
        super(id, vehicleName);

    }

    @Override
    protected void setId(int id) {
        super.setId(id);
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

    @Override
    public int getId() {
        return this.id;
    }
}
