package core.commands.CreateCommand.listing;

import core.contracts.Command;
import core.contracts.LogisticRepository;
import exceptions.InvalidUserInputException;
import models.contracts.Vehicle;
import utils.ListingHelpers;

import java.util.List;
import java.util.stream.Collectors;

public class ListFreeVehiclesCommand implements Command {
    private final List<Vehicle> freeVehicles;
    public static final String NO_FREE_VEHICLES_ERROR = "There are no free trucks at the moment.";

    public ListFreeVehiclesCommand(LogisticRepository logisticRepository) {
        this.freeVehicles = logisticRepository.getAllVehicles()
                .stream()
                .filter(Vehicle::isFree)
                .collect(Collectors.toList());
    }

    @Override
    public String execute(List<String> parameters) {
        if (freeVehicles.isEmpty()) {
            throw new InvalidUserInputException(NO_FREE_VEHICLES_ERROR);
        }
        return ListingHelpers.vehiclesToString(freeVehicles);
    }
}
