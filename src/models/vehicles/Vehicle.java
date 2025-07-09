package models.vehicles;

import models.VehicleName;

public abstract class Vehicle implements models.contracts.Vehicle {
    protected final int id;
    protected int range;
    protected int capacity;
    protected final VehicleName name;
    protected boolean isFree = true;

    public Vehicle(int id, VehicleName vehicleName){
        this.id = id;
        this.name = vehicleName;
        this.capacity = setCapacity();
        this.range = setRange();
    }

    protected abstract int setRange();

    protected abstract int setCapacity();

    public void setFree(boolean free) {
        this.isFree = free;
    }

    @Override
    public int getId() {
        return id;
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
    public boolean isFree() {
        return this.isFree;
    }

}
