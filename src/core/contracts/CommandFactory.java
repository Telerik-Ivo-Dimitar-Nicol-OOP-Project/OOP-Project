package core.contracts;

public interface CommandFactory {
    Command createCommandFromCommandName(String commandTypeAsString, LogisticRepository logisticRepository);
}
