package group.project.oop.tests.commands;

import core.LogisticRepositoryImpl;
import core.commands.CreateCommand.listing.FindRouteCommand;
import core.commands.CreateCommand.listing.ListPackagesCommand;
import core.contracts.Command;
import core.contracts.LogisticRepository;
import exceptions.InvalidUserInputException;
import models.Location;
import models.PackageImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ListPackagesTests {
    private Command command;
    private LogisticRepository repository;

    @BeforeEach
    public void before() {
        this.repository = new LogisticRepositoryImpl();
        this.command = new ListPackagesCommand(repository);
    }
    @Test
    public void shouldThrowException_When_No_Packages_exists(){
        List<String> params = new ArrayList<>(List.of("1"));
        Assertions.assertThrows(InvalidUserInputException.class, () -> command.execute(params));
    }


}
