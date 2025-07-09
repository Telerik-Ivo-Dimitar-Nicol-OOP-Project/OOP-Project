package core.commands.CreateCommand.listing;

import core.contracts.Command;
import core.contracts.LogisticRepository;
import exceptions.InvalidUserInputException;
import models.contracts.Vehicle;
import utils.ListingHelpers;

import java.util.List;

public class ListTrucksCommand implements Command {

    private final List<Vehicle> vehicles;
    public static final String NO_VEHICLES_ERROR = "There are no trucks available.";

    public ListTrucksCommand(LogisticRepository logisticRepository){
        vehicles = logisticRepository.getAllVehicles();
    }

    @Override
    public String execute(List<String> parameters) {
        if (vehicles.isEmpty()) {
            throw new InvalidUserInputException(NO_VEHICLES_ERROR);
        }

        return ListingHelpers.vehiclesToString(vehicles);
    }




}
