package core.commands.CreateCommand;

import core.contracts.Command;
import core.contracts.LogisticRepository;
import exceptions.InvalidUserInputException;
import models.Routes.DeliveryRouteImpl;
import models.contracts.DeliveryRoute;
import models.contracts.Package;

import java.util.List;

public class CompleteRouteCommand implements Command {
    private final LogisticRepository repository;

    public CompleteRouteCommand(LogisticRepository logisticRepository) {
        this.repository = logisticRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.isEmpty()) {
            throw new InvalidUserInputException("Route ID is required.");
        }
        int routeId = Integer.parseInt(parameters.get(0));
        DeliveryRoute route = repository.getRoutes().stream()
                .filter(r -> r.getRouteID() == routeId)
                .findFirst()
                .orElseThrow(() -> new InvalidUserInputException("Route not found."));

        if (DeliveryRouteImpl.getStatusFlag()) {
            throw new InvalidUserInputException("Route is already completed.");
        }

        for (Package pkg : route.getAssignedPackages()) {
            pkg.setDelivered();
            System.out.println("Package with ID " + pkg.getId() + " has been marked as delivered. ETA: " +
                    (pkg instanceof models.PackageImpl pkgImpl ? pkgImpl.getEta() : "N/A"));
        }
        DeliveryRouteImpl.changeRouteStatus();
        return "Route " + routeId + " marked as completed. All packages delivered.";
    }
}