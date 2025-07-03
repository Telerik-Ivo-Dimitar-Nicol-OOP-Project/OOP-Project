package core.commands.creation;

import core.contracts.Command;
import core.contracts.LogisticRepository;
import models.Location;
import models.contracts.DeliveryRoute;
import models.contracts.Package;
import utils.ParsingHelpers;

import java.time.LocalDateTime;
import java.util.List;

public class CreateRouteCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    private static final String ROUTE_CREATED_MESSAGE = "Route with ID %s was created.";
    private static final String LOCATION_ERROR_MESSAGE = "Input Location is not a valid location";


    private final LogisticRepository repository;
    private Location startLocation;
    private Location endLocation;

    public CreateRouteCommand(LogisticRepository logisticRepository) {
        this.repository = logisticRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        // ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);
        DeliveryRoute newRoute = repository.createRoute(startLocation, endLocation);
        return String.format(ROUTE_CREATED_MESSAGE, newRoute.getRouteID());


    }
    private void parseParameters(List<String> parameters) {
        startLocation = ParsingHelpers.tryParseEnum(parameters.get(0), Location.class, LOCATION_ERROR_MESSAGE );
        endLocation = ParsingHelpers.tryParseEnum(parameters.get(1), Location.class, LOCATION_ERROR_MESSAGE );


    }

}
