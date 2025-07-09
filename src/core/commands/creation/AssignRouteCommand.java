package core.commands.creation;

import core.contracts.Command;
import core.contracts.LogisticRepository;
import exceptions.InvalidUserInputException;
import models.contracts.DeliveryRoute;
import models.contracts.Package;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.util.List;

public class AssignRouteCommand implements Command {
    public static final String ID_OF_PACKAGE_NEEDS_TO_BE_INTEGER_NUMBER = "ID Of package needs to be a n Integer number";
    public static final String ID_OF_ROUTE_NEEDS_TO_BE_INTEGER_NUMBER = "ID Of Route needs to be a n Integer number";
    public static final String ROUTE_NOT_SUITABLE_FOR_PACKAGE_ERROR = "Route with id : %d is not suitable for package with id: %d";
    public static final String ASSIGNED_TO_ROUTE_ERROR = "Package is already assigned to route";
    public static final String ASSIGNED_TO_ROUTE = "Package: %d assigned to route %d";
    private final LogisticRepository repository;

    public AssignRouteCommand(LogisticRepository repository){
        this.repository=repository;
    }
    @Override
    public String execute(List<String> parameters) {
        int idOfPackage = ParsingHelpers.tryParseInteger(parameters.get(0), ID_OF_PACKAGE_NEEDS_TO_BE_INTEGER_NUMBER);
        int idOfRoute = ParsingHelpers.tryParseInteger(parameters.get(1), ID_OF_ROUTE_NEEDS_TO_BE_INTEGER_NUMBER);

        Package packageToAdd = repository.getPackageById(idOfPackage);
        DeliveryRoute deliveryRouteToAddTo = repository.getRouteById(idOfRoute);

        if (!ValidationHelpers.validateSuitableRoute(packageToAdd, deliveryRouteToAddTo)){
            throw new InvalidUserInputException(String.format(ROUTE_NOT_SUITABLE_FOR_PACKAGE_ERROR, idOfRoute, idOfPackage));
        }
        if (packageToAdd.isAssignedToRoute()){
            throw new InvalidUserInputException(ASSIGNED_TO_ROUTE_ERROR);
        }
        deliveryRouteToAddTo.addPackageToRoute(packageToAdd);
        return String.format(ASSIGNED_TO_ROUTE, idOfPackage, idOfRoute);

    }
}
