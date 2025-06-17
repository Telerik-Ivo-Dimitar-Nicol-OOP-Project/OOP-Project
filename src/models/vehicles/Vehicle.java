package models.vehicles;

import models.VehicleName;

public abstract class Vehicle implements models.contracts.Vehicle {
    protected int id;
    protected int range;
    protected int capacity;
    VehicleName name;

    public Vehicle(int id, VehicleName vehicleName){
        setId(id);
        this.name = vehicleName;
        setRange();
        setCapacity();
    }
    protected void setId(int id){
        this.id = id;

    }
    protected void setRange(){
        switch (name){
            case Man -> this.range = 10000;
            case Actros -> this.range = 13000;
            case Scania -> this.range = 8000;
        }
    }
    protected void setCapacity(){
        switch (name){
            case Scania -> this.capacity = 42000;
            case Man -> this.capacity = 37000;
            case Actros -> this.capacity = 26000;
        }
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




}
