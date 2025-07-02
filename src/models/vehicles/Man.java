package models.vehicles;

import models.VehicleName;

public class Man extends Vehicle {
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


}
