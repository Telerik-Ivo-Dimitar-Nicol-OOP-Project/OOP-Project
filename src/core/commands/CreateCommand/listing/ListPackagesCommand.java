package core.commands.CreateCommand.listing;

import core.contracts.Command;
import core.contracts.LogisticRepository;
import exceptions.InvalidUserInputException;
import models.PackageImpl;
import utils.ListingHelpers;

import java.util.List;

public class ListPackagesCommand implements Command {
    private final List<PackageImpl> packages;
    public static final String NO_PACKAGES_ERROR = "There are no packages created.";

    public ListPackagesCommand(LogisticRepository logisticRepository){
        packages = logisticRepository.getPackages();
    }
    @Override
    public String execute(List<String> parameters) {
        if (packages.isEmpty()) {
            throw new InvalidUserInputException(NO_PACKAGES_ERROR);
        }
        return ListingHelpers.packagesToString(packages);
    }
}
