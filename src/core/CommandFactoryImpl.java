package core;

import core.contracts.Command;
import core.commands.CreateCommand.enums.CommandType;
import core.commands.CreateCommand.listing.ListPackagesCommand;
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

            default:
                throw new IllegalArgumentException(String.format(INVALID_COMMAND, commandName));
        }
    }
}
