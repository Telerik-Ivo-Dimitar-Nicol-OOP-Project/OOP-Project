package core;

import core.commands.CreateCommand.listing.*;
import core.commands.creation.AddCheckpointCommand;
import core.commands.creation.AssignRouteCommand;
import core.commands.creation.CreateRouteCommand;
import core.contracts.Command;
import core.commands.CreateCommand.enums.CommandType;
import core.commands.creation.CreatePackageCommand;
import core.contracts.CommandFactory;
import core.contracts.LogisticRepository;
import utils.ParsingHelpers;

public class CommandFactoryImpl implements CommandFactory {
    private static final String INVALID_COMMAND = "Invalid command name: %s!";

    public Command createCommandFromCommandName(String commandName, LogisticRepository logisticRepository) {
        CommandType commandType = ParsingHelpers.tryParseEnum(commandName, CommandType.class, String.format(INVALID_COMMAND, commandName));

        switch (commandType) {
            case CREATEPACKAGE:
                return new CreatePackageCommand(logisticRepository);
            case LISTPACKAGES:
                return new ListPackagesCommand(logisticRepository);
            case CREATEROUTE:
                return new CreateRouteCommand(logisticRepository);
            case FINDROUTE:
                return new FindRouteCommand(logisticRepository);
            case ADDCHECKPOINT:
                return new AddCheckpointCommand(logisticRepository);
            case ASSIGNROUTE:
                return new AssignRouteCommand(logisticRepository);
            case LISTTRUCKS:
                return new ListTrucksCommand(logisticRepository);
            case LISTFREEVEHICLES:
                return new ListFreeVehiclesCommand(logisticRepository);
            case LISTROUTES:
                return new ListRoutesCommand(logisticRepository.getRoutes());

            default:
                throw new IllegalArgumentException(String.format(INVALID_COMMAND, commandName));
        }
    }
}
