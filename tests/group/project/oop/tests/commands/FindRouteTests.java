package group.project.oop.tests.commands;

import core.LogisticRepositoryImpl;
import core.commands.CreateCommand.listing.FindRouteCommand;
import core.contracts.Command;
import core.contracts.LogisticRepository;
import exceptions.InvalidUserInputException;
import models.Location;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FindRouteTests {
    private Command command;
    private LogisticRepository repository;

    @BeforeEach
    public void before() {
        this.repository = new LogisticRepositoryImpl();
        this.command = new FindRouteCommand(repository);
    }
    @Test
    public void shouldThrowException_When_Package_DoesNot_Exist(){
        Assertions.assertThrows(InvalidUserInputException.class, () -> repository.getPackageById(1));
    }

}
