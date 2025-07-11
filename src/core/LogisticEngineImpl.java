package core;

import core.contracts.Command;
import core.contracts.CommandFactory;
import core.contracts.Engine;
import core.contracts.LogisticRepository;
import exceptions.InvalidUserInputException;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LogisticEngineImpl implements Engine {

    private static final String TERMINATION_COMMAND = "Exit";
    private static final String EMPTY_COMMAND_ERROR = "Command cannot be empty.";

    private final CommandFactory commandFactory;
    private final LogisticRepository logisticRepository;
    private PrintWriter writer;
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public LogisticEngineImpl() {
        this.commandFactory = new CommandFactoryImpl();
        this.logisticRepository = new LogisticRepositoryImpl();
        try {
            writer =  new PrintWriter(new FileWriter("Logs.txt", true));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void start() {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                try {
                    String inputLine = scanner.nextLine();
                    if (inputLine.isBlank()) {
                        System.out.println(EMPTY_COMMAND_ERROR);
                        continue;
                    }
                    if (inputLine.equalsIgnoreCase(TERMINATION_COMMAND)) {
                        writer.close();
                        break;
                    }
                    processCommand(inputLine);
                } catch (InvalidUserInputException | IllegalArgumentException ex) {
                    String timestamp = LocalDateTime.now().format(DATE_TIME_FORMATTER);
                    if (ex.getMessage() != null && !ex.getMessage().isEmpty()) {
                        System.out.println(ex.getMessage());
                        writer.printf("%s %s%n", timestamp, ex.getMessage());
                        writer.flush();
                    } else {
                        System.out.println(ex.toString());
                    }
                }
            }
        } finally {
            writer.close();
        }
    }

    private void processCommand(String inputLine) {
        String commandName = extractCommandName(inputLine);
        Command command = commandFactory.createCommandFromCommandName(commandName, logisticRepository);
        List<String> parameters = extractCommandParameters(inputLine);
        String executionResult = command.execute(parameters);
        String timestamp = LocalDateTime.now().format(DATE_TIME_FORMATTER);
        this.writer.printf("%s %s%n", timestamp, executionResult);
        this.writer.flush();
        System.out.println(executionResult);
    }

    /**
     * Receives a full line and extracts the command to be executed from it.
     * For example, if the input line is "FilterBy Assignee John", the method will return "FilterBy".
     *
     * @param inputLine A complete input line
     * @return The name of the command to be executed
     */
    private String extractCommandName(String inputLine) {
        return inputLine.split(" ")[0];
    }

    /**
     * Receives a full line and extracts the parameters that are needed for the command to execute.
     * For example, if the input line is "FilterBy Assignee John",
     * the method will return a list of ["Assignee", "John"].
     *
     * @param inputLine A complete input line
     * @return A list of the parameters needed to execute the command
     */
    private List<String> extractCommandParameters(String inputLine) {
        String[] commandParts = inputLine.split(" ");
        List<String> parameters = new ArrayList<>();
        for (int i = 1; i < commandParts.length; i++) {
            parameters.add(commandParts[i]);
        }
        return parameters;
    }


}
