package core.commands.CreateCommand.listing;

import core.contracts.Command;
import exceptions.InvalidUserInputException;
import models.contracts.DeliveryRoute;
import utils.ListingHelpers;

import java.util.List;

public class ListRoutesCommand implements Command {

    private final List<DeliveryRoute> routes;
    public static final String NO_ROUTES_ERROR = "There are no routes created yet.";

    public ListRoutesCommand(List<DeliveryRoute> routes) {
        this.routes = routes;
    }

    @Override
    public String execute(List<String> parameters) {
        if (routes.isEmpty()) {
            throw new InvalidUserInputException(NO_ROUTES_ERROR);
        }

        return ListingHelpers.routesToString(routes);
    }
}