package core.commands.creation;

import core.contracts.Command;
import core.contracts.LogisticRepository;
import models.Location;
import models.contracts.Package;
import utils.ParsingHelpers;

import java.util.List;

public class CreatePackageCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;
    private static final String PACKAGE_CREATED_MESSAGE = "Package with ID %d was created.";
    private static final String LOCATION_ERROR_MESSAGE = "Location is not a valid location";


    private final LogisticRepository repository;
    private Location startLocation;
    private Location endLocation;
    private double weight;
    private String contact;

    public CreatePackageCommand(LogisticRepository logisticRepository) {
        this.repository = logisticRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        // ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);
        Package newPackage = repository.createPackage(startLocation, endLocation, weight, contact);
        return String.format(PACKAGE_CREATED_MESSAGE, newPackage.getId());


    }
    private void parseParameters(List<String> parameters) {
        startLocation = ParsingHelpers.tryParseEnum(parameters.get(0), Location.class, LOCATION_ERROR_MESSAGE );
        endLocation = ParsingHelpers.tryParseEnum(parameters.get(1), Location.class, LOCATION_ERROR_MESSAGE );
        weight = ParsingHelpers.tryParseDouble(parameters.get(2),"weight" );
        StringBuilder sb = new StringBuilder();
        for (int i = 3; i < parameters.size(); i++) {
            sb.append(parameters.get(i) + " ");
        }
        contact = sb.toString();

    }

}
