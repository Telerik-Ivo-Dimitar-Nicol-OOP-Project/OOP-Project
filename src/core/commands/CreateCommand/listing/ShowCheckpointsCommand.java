package core.commands.CreateCommand.listing;

import core.contracts.Command;
import core.contracts.LogisticRepository;
import exceptions.InvalidUserInputException;
import models.Routes.Checkpoint;
import models.contracts.DeliveryRoute;
import utils.ParsingHelpers;

import java.util.List;
import java.util.stream.Collectors;

public class ShowCheckpointsCommand implements Command {

    private final LogisticRepository repository;

    public ShowCheckpointsCommand(LogisticRepository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != 1) {
            throw new InvalidUserInputException("Usage: showcheckpoints <routeId>");
        }

        int routeId = ParsingHelpers.tryParseInteger(parameters.get(0), "Route ID must be a valid number");

        DeliveryRoute route = repository.getRoutes().stream()
                .filter(r -> r.getRouteID() == routeId)
                .findFirst()
                .orElseThrow(() -> new InvalidUserInputException("Route with ID " + routeId + " not found."));

        List<Checkpoint> checkpoints = route.getCalculatedCheckpoints();

        if (checkpoints.isEmpty()) {
            return "No calculated checkpoints found for this route.";
        }

        return checkpoints.stream()
                .map(Checkpoint::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}