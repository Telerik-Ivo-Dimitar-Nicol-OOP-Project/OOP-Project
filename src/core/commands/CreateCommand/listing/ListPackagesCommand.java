package core.commands.CreateCommand.listing;

import core.contracts.Command;
import core.contracts.LogisticRepository;
import models.PackageImpl;
import utils.ListingHelpers;

import java.util.List;

public class ListPackagesCommand implements Command {
    private final List<PackageImpl> packages;

    public ListPackagesCommand(LogisticRepository logisticRepository){
        packages = logisticRepository.getPackages();
    }
    @Override
    public String execute(List<String> parameters) {
        if (packages.isEmpty()) {
            return "There are no registered journeys.";
        }
        return ListingHelpers.packagesToString(packages);
    }
}
