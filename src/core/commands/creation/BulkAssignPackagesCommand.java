package core.commands.creation;

import core.contracts.Command;
import core.contracts.LogisticRepository;
import exceptions.InvalidUserInputException;
import models.contracts.DeliveryRoute;
import utils.ParsingHelpers;
import models.contracts.Package;

import java.util.ArrayList;
import java.util.List;

public class BulkAssignPackagesCommand implements Command {

    private final LogisticRepository repository;

    public BulkAssignPackagesCommand(LogisticRepository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() < 2) {
            throw new InvalidUserInputException("Usage: Bulkassign <routeId> <packageId1> <packageId2> ...");
        }

        int routeId = ParsingHelpers.tryParseInteger(parameters.get(0), "Route ID must be a valid number");
        DeliveryRoute route = repository.getRoutes().stream()
                .filter(r -> r.getRouteID() == routeId)
                .findFirst()
                .orElseThrow(() -> new InvalidUserInputException("Route with ID " + routeId + " not found."));

        List<Integer> packageIds = new ArrayList<>();
        for (int i = 1; i < parameters.size(); i++) {
            packageIds.add(ParsingHelpers.tryParseInteger(parameters.get(i), "Package ID must be a valid number"));
        }

        StringBuilder result = new StringBuilder();
        for (int packageId : packageIds) {
            Package p = repository.getPackages().stream()
                    .filter(pkg -> pkg.getId() == packageId)
                    .findFirst()
                    .orElse(null);

            if (p == null) {
                result.append(String.format("Package with ID %d not found.%n", packageId));
                continue;
            }

            if (!route.isSuitableForPackage(p)) {
                result.append(String.format("Package %d cannot be added – weight or destination incompatible.%n", packageId));
                continue;
            }

            try {
                route.addPackageToRoute(p);
                result.append(String.format("Package %d assigned successfully.%n", packageId));
            } catch (Exception e) {
                result.append(String.format("Error assigning package %d: %s%n", packageId, e.getMessage()));
            }
        }

        return result.toString().trim();
    }
}
