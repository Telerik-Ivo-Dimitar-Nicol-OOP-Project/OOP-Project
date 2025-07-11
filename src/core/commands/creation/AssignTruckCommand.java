package core.commands.creation;

import core.contracts.Command;
import core.contracts.LogisticRepository;
import exceptions.InvalidUserInputException;
import models.contracts.DeliveryRoute;
import models.contracts.Vehicle;

import java.util.List;

public class AssignTruckCommand implements Command {

    private final LogisticRepository repository;

    public AssignTruckCommand(LogisticRepository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != 2) {
            throw new InvalidUserInputException("Expected: assigntruck <routeId> <truckId>");
        }

        int routeId = Integer.parseInt(parameters.get(0));
        int truckId = Integer.parseInt(parameters.get(1));

        DeliveryRoute route = repository.getRoutes().stream()
                .filter(r -> r.getRouteID() == routeId)
                .findFirst()
                .orElseThrow(() -> new InvalidUserInputException("Route not found with ID: " + routeId));

        if (route.getAssignedVehicle() != null) {
            throw new InvalidUserInputException("This route already has a truck assigned.");
        }

        Vehicle truck = repository.getVehicleById(truckId);

        if (!truck.isFree()) {
            throw new InvalidUserInputException("Truck is already assigned to another route.");
        }

        route.assignVehicle(truck);
        return String.format("Truck %d successfully assigned to route %d.", truckId, routeId);
    }
}