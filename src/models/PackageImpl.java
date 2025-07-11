package models;

import models.contracts.Package;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

public class PackageImpl implements Package {
    public static final double PACKAGE_MIN_WEIGHT = 0.1;
    public static final double PACKAGE_MAX_WEIGHT = 5000;
    public static final String PACKAGE_OUTSIDE_OF_LIMIT = "Package must be between 0.1 KG and 5000 KG";
    public static final int CONTACT_MIN_LENGHT = 10;
    public static final int CONTACT_MAX_LENGHT = 50;
    public static final String CONTACT_ERRROR_MESSAGE = "Contact information" +
            "must be between 10 & 50 characters";
    public static final String NOT_ASSIGNED_TO_ROUTE = "Not assigned to route";


    private final int id;
    private Location startLocation;
    private Location endLocation;
    private double weight;
    private String contact;
    private String idOfRouteToWhichPackageIsAssigned;
    private boolean assignedToRoute;
    private boolean isDelivered;



    public PackageImpl(int id, Location startLocation, Location endLocation, double weight, String contact){
        this.id = id;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        setWeight(weight);
        setContact(contact);
        assignedToRoute = false;
        isDelivered = false;
        idOfRouteToWhichPackageIsAssigned = NOT_ASSIGNED_TO_ROUTE;

    }

    @Override
    public String getRouteIdToWhichPackageIsAssigned() {
        return idOfRouteToWhichPackageIsAssigned;
    }

    @Override
    public void setRouteIdToWhichPackageIsAssigned(int routeId) {
        this.idOfRouteToWhichPackageIsAssigned = Integer.toString(routeId);

    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered() {
        isDelivered = true;
    }

    public boolean isAssignedToRoute() {
        return assignedToRoute;
    }

    public void setAssignedToRoute(boolean assignedToRoute) {
        this.assignedToRoute = assignedToRoute;
    }

    private void setWeight(double weight){
        ValidationHelpers.validateWeightIsCorrect(weight, PACKAGE_MIN_WEIGHT, PACKAGE_MAX_WEIGHT,PACKAGE_OUTSIDE_OF_LIMIT);
        this.weight = weight;
    }
    private void setContact(String contact){
        ValidationHelpers.validateStringLength(contact,CONTACT_MIN_LENGHT, CONTACT_MAX_LENGHT, CONTACT_ERRROR_MESSAGE);
        this.contact = contact;
    }


    @Override
    public Location getStartLocation() {
        return startLocation;
    }

    @Override
    public Location getEndLocation() {
        return endLocation;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public String getContactInformation() {
        return contact;
    }

    @Override
    public int getId() {
        return id;
    }
    @Override
    public String toString(){
        return String.format("Package with id: %d%n Weight: %.2f%n Start & End Location: %s - %s%n contact information: " +
                "%s%nAssigned to route with ID: %s%n", getId(), getWeight(), getStartLocation(), getEndLocation(), getContactInformation(), getRouteIdToWhichPackageIsAssigned());
    }

    public String getIdOfRouteToWhichPackageIsAssigned() {
        return idOfRouteToWhichPackageIsAssigned;
    }


    public String getContact() {
        return contact;
    }

}
