package core.commands.creation;

import core.contracts.Command;
import core.contracts.LogisticRepository;
import exceptions.InvalidUserInputException;
import models.Location;
import models.contracts.DeliveryRoute;

import java.util.List;

import static utils.ValidationHelpers.isValidLocation;

public class AddCheckpointCommand implements Command {

    private final LogisticRepository repository;

    public AddCheckpointCommand(LogisticRepository repository){
        this.repository=repository;
    }

    @Override
    public String execute(List<String> args) {
        if (args.size() != 2) {
            throw new InvalidUserInputException("Usage: addCheckpointToRoute <routeId> <checkpoint>");
        }

        String routeId = args.get(0).trim();
        String checkpointName = args.get(1).trim().toUpperCase();

        if (!isValidLocation(checkpointName)) {
            throw new InvalidUserInputException("Invalid checkpoint location: " + checkpointName);
        }

        Location checkpoint = Location.valueOf(checkpointName);
        DeliveryRoute route = repository.getRouteById(Integer.parseInt(routeId));

        if (route instanceof models.Routes.DeliveryRouteImpl deliveryRoute) {
            deliveryRoute.addCheckpoint(checkpoint);
            deliveryRoute.calculateCheckpointsWithArrivalTimes(87);
            return String.format("Checkpoint %s added to route %s.", checkpoint, route.getRouteID());
        }

        throw new InvalidUserInputException("Unsupported route implementation.");
    }
}
