package core.commands.CreateCommand.listing;

import core.contracts.Command;
import core.contracts.LogisticRepository;
import exceptions.InvalidUserInputException;
import models.contracts.Package;

import java.util.List;

public class ViewPackageByIdCommand implements Command {

    private final LogisticRepository repository;

    public ViewPackageByIdCommand(LogisticRepository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() != 1) {
            throw new InvalidUserInputException("Expected 1 parameter: package ID.");
        }

        int packageId;
        try {
            packageId = Integer.parseInt(parameters.get(0));
        } catch (NumberFormatException e) {
            throw new InvalidUserInputException("Package ID must be a number.");
        }

        Package pack = repository.getPackages()
                .stream()
                .filter(p -> p.getId() == packageId)
                .findFirst()
                .orElseThrow(() -> new InvalidUserInputException("Package with ID " + packageId + " not found."));

        StringBuilder sb = new StringBuilder();
        sb.append("Package ID: ").append(pack.getId()).append(System.lineSeparator());
        sb.append("From: ").append(pack.getStartLocation()).append(System.lineSeparator());
        sb.append("To: ").append(pack.getEndLocation()).append(System.lineSeparator());
        sb.append("Weight: ").append(pack.getWeight()).append(" kg").append(System.lineSeparator());
        sb.append("Contact: ").append(pack.getContactInformation()).append(System.lineSeparator());
        sb.append("Assigned to route: ").append(pack.isAssignedToRoute() ? "Yes" : "No").append(System.lineSeparator());

        return sb.toString();
    }
}