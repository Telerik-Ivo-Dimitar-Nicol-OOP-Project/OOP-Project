package core.commands.SaveAndRestoreCommands;

import core.commands.CreateCommand.listing.ListFreeVehiclesCommand;
import core.commands.CreateCommand.listing.ListPackagesCommand;
import core.commands.CreateCommand.listing.ListRoutesCommand;
import core.commands.CreateCommand.listing.ListTrucksCommand;
import core.contracts.Command;
import core.contracts.LogisticRepository;
import utils.ListingHelpers;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SaveStateCommand implements Command {

    private final LogisticRepository repository;

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public SaveStateCommand(LogisticRepository logisticRepository) {
        this.repository = logisticRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        String listTrucks = new ListTrucksCommand(repository).execute(List.of());
        String listOfPackages = new ListPackagesCommand(repository).execute(List.of());
        String listOfRoutes = ListingHelpers.routesToString(repository.getRoutes());
        String timestamp = LocalDateTime.now().format(DATE_TIME_FORMATTER);

        try (PrintWriter writer = new PrintWriter(new FileWriter("State.txt", false))) {
            writer.printf("%s%n List of packages:%n%s%n",timestamp, listOfPackages);
            writer.printf("List of routes:%n%s%n", listOfRoutes);
            writer.printf("%n%s%n", listTrucks);
        } catch (IOException e) {
            throw new RuntimeException("Error saving state", e);
        }

        return "Successfully saved state of program to a file";
    }
}
