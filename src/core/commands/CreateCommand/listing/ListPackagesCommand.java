package core.commands.CreateCommand.listing;

import core.contracts.Command;
import core.contracts.LogisticRepository;
import exceptions.InvalidUserInputException;
import models.PackageImpl;
import utils.ListingHelpers;

import java.util.List;

public class ListPackagesCommand implements Command {

    public static final String NO_PACKAGES_ERROR = "There are no packages created.";
    private final LogisticRepository repository;

    public ListPackagesCommand(LogisticRepository logisticRepository){
        this.repository = logisticRepository;
    }
    @Override
    public String execute(List<String> parameters) {
        List<PackageImpl> packages = repository.getPackages();
        if (packages.isEmpty()) {
            throw new InvalidUserInputException(NO_PACKAGES_ERROR);
        }
        return ListingHelpers.packagesToString(packages);
    }
}
