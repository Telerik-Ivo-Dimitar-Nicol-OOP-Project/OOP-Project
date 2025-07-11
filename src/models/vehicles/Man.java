package models.vehicles;

import models.VehicleName;

public class Man extends Vehicle {
    private boolean isFree = true;

    public Man(int id){
        super(id, VehicleName.MAN);
    }

    @Override
    protected int setRange() {
        return 10000;
    }

    @Override
    protected int setCapacity() {
        return 37000;
    }

    @Override
    public boolean isFree() {
        return isFree;
    }

    @Override
    public void setFree(boolean isFree) {
        this.isFree = isFree;
    }


}
