package models.vehicles;

import models.VehicleName;

public class Actros extends Vehicle{
    public Actros(int id){
        super(id, VehicleName.Actros);
    }

    @Override
    protected int setRange() {
        return 13000;
    }

    @Override
    protected int setCapacity() {
        return 26000;
    }

}
