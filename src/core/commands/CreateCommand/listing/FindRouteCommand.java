package core.commands.CreateCommand.listing;

import core.contracts.Command;
import core.contracts.LogisticRepository;
import exceptions.InvalidUserInputException;
import models.Location;
import models.PackageImpl;
import models.Routes.DeliveryRouteImpl;
import models.contracts.DeliveryRoute;
import models.contracts.Package;
import utils.ListingHelpers;
import utils.ParsingHelpers;
import utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class FindRouteCommand implements Command {
    public static final String THERE_ARE_NO_ROUTES_CREATED_YET_ERROR = "There are no routes created yet";
    public static final String ALREADY_DELIVERED = "Package is already delivered";
    private final LogisticRepository repository;
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String INPUT_NOT_INT_ERROR = "Package Id to search routes for needs to be a whole number.";

    public FindRouteCommand(LogisticRepository logisticRepository){
        this.repository = logisticRepository;

    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int packageIdToFindRoutes = ParsingHelpers.tryParseInteger(parameters.get(0), INPUT_NOT_INT_ERROR);
        Package packageToCheckRoutesFor = repository.getPackageById(packageIdToFindRoutes);
        if (packageToCheckRoutesFor.isDelivered()){
            throw new IllegalArgumentException(ALREADY_DELIVERED);
        }
        if (packageToCheckRoutesFor.isAssignedToRoute()){
            throw new IllegalArgumentException(String.format("Package %d is already assigned to route %s", packageToCheckRoutesFor.getId(), packageToCheckRoutesFor.getRouteIdToWhichPackageIsAssigned()));
        }
        List<DeliveryRoute> suitableRoutesForPackage = getSuitableRoutesForPackage(packageToCheckRoutesFor);
        return ListingHelpers.routesToString(suitableRoutesForPackage);
    }
    private List<DeliveryRoute> getSuitableRoutesForPackage(Package packageToCheckRoutesFor) {
        List<DeliveryRouteImpl> routes = repository.getRoutes();
        if (routes.isEmpty()){
            throw new InvalidUserInputException(THERE_ARE_NO_ROUTES_CREATED_YET_ERROR);
        }
        List<DeliveryRoute> suitableRoutesForPackage = new ArrayList<>();

        for (DeliveryRoute route : routes){
            // this validation helper can be reused to check if route is valid for a given package
            if (ValidationHelpers.validateSuitableRoute(packageToCheckRoutesFor, route)){
                suitableRoutesForPackage.add(route);
            }
        }
        if (suitableRoutesForPackage.isEmpty()){
            throw new InvalidUserInputException("No routes that are suitable for this package are available");
        }
        return suitableRoutesForPackage;

    }
}
