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
    private final List<DeliveryRouteImpl> routes;
    private final LogisticRepository repository;
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String INPUT_NOT_INT_ERROR = "Package Id to search routes for needs to be a whole number.";

    public FindRouteCommand(LogisticRepository logisticRepository){
        this.repository = logisticRepository;
        routes = logisticRepository.getRoutes();
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int packageIdToFindRoutes = ParsingHelpers.tryParseInteger(parameters.get(0), INPUT_NOT_INT_ERROR);
        Package packageToCheckRoutesFor = repository.getPackageById(packageIdToFindRoutes);
        List<DeliveryRoute> suitableRoutesForPackage = getSuitableRoutesForPackage(packageToCheckRoutesFor);
        return ListingHelpers.routesToString(suitableRoutesForPackage);
    }
    private List<DeliveryRoute> getSuitableRoutesForPackage(Package packageToCheckRoutesFor) {
        if (routes.isEmpty()){
            throw new InvalidUserInputException(THERE_ARE_NO_ROUTES_CREATED_YET_ERROR);
        }
        List<DeliveryRoute> suitableRoutesForPackage = new ArrayList<>();
        Location startLocation = packageToCheckRoutesFor.getStartLocation();
        Location endLocation = packageToCheckRoutesFor.getEndLocation();
        for (DeliveryRoute route : routes){
            boolean hasSuitableStartLocation = false;
            boolean hasSuitableEndLocation = false;
            List<String> routeLocations = route.getLocations();
            int indexOfStarLocation = 0;
            for (int i = 0; i < routeLocations.size(); i++) {
                if (routeLocations.get(i).equals(startLocation.toString())){
                    hasSuitableStartLocation = true;
                    indexOfStarLocation = i;
                    break;
                }
            }

            for (int i = indexOfStarLocation + 1; i < routeLocations.size(); i++) {
                if (routeLocations.get(i).equals(endLocation.toString())){
                    hasSuitableEndLocation = true;
                    break;
                }
            }
            if (hasSuitableStartLocation && hasSuitableEndLocation){
                suitableRoutesForPackage.add(route);
            }
        }
        if (suitableRoutesForPackage.isEmpty()){
            throw new InvalidUserInputException("No routes that are suitable for this package are available");
        }
        return suitableRoutesForPackage;

    }
}
