package core.commands.creation;

import core.contracts.Command;
import core.contracts.LogisticRepository;
import models.Location;
import models.contracts.DeliveryRoute;
import models.contracts.Package;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CreateRouteCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    private static final String ROUTE_CREATED_MESSAGE = "Route with ID %s was created.";
    private static final String LOCATION_ERROR_MESSAGE = "Input Location is not a valid location";
    private static final String DATE_TIME_FORMATTER = "yyyy-MM-dd_HH:mm";
    private static final String INVALID_DATE_FORMAT_MESSAGE = "Invalid date format. Use 'yyyy-MM-dd_HH:mm' format.";


    private final LogisticRepository repository;
    private Location startLocation;
    private Location endLocation;
    private LocalDateTime departureTime;

    public CreateRouteCommand(LogisticRepository logisticRepository) {
        this.repository = logisticRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);
        DeliveryRoute newRoute = repository.createRoute(startLocation, endLocation, departureTime);
        newRoute.calculateCheckpointsWithArrivalTimes(87);
        return String.format(ROUTE_CREATED_MESSAGE, newRoute.getRouteID());
    }

    private void parseParameters(List<String> parameters) {
        startLocation = ParsingHelpers.tryParseEnum(parameters.get(0), Location.class, LOCATION_ERROR_MESSAGE );
        endLocation = ParsingHelpers.tryParseEnum(parameters.get(1), Location.class, LOCATION_ERROR_MESSAGE );
        departureTime = ParsingHelpers.tryParseDateTime(parameters.get(2), DATE_TIME_FORMATTER, INVALID_DATE_FORMAT_MESSAGE);


    }

}
