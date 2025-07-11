package models.vehicles;

import models.VehicleName;

public class Actros extends Vehicle{

    private boolean isFree = true;

    public Actros(int id){
        super(id, VehicleName.ACTROS);
    }

    @Override
    protected int setRange() {
        return 13000;
    }

    @Override
    protected int setCapacity() {
        return 26000;
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
