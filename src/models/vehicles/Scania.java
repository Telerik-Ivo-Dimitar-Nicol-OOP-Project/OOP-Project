package models.vehicles;

import models.VehicleName;

public class Scania extends Vehicle implements models.contracts.Vehicle {

    public Scania(int id){
        super(id, VehicleName.SCANIA);

    }

    @Override
    protected int setRange() {
        return  8000;
    }

    @Override
    protected int setCapacity() {
        return 42000;
    }


}
