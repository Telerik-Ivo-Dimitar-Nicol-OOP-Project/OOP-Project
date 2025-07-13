package group.project.oop.tests.commands;

import core.LogisticRepositoryImpl;
import core.commands.CreateCommand.listing.ViewPackageByIdCommand;
import models.Location;
import models.PackageImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ViewPackageByIdCommandTests {

    private LogisticRepositoryImpl repository;
    private ViewPackageByIdCommand command;

    @BeforeEach
    public void setup() {
        repository = new LogisticRepositoryImpl();
        command = new ViewPackageByIdCommand(repository);
    }

    @Test
    public void execute_Should_ReturnPackageInfo_When_PackageExists() {

        Location start = Location.SYD;
        Location end = Location.MEL;
        PackageImpl pack = repository.createPackage(start, end, 45.0, "john.doe@example.com");

        String result = command.execute(List.of(String.valueOf(pack.getId())));

        assertTrue(result.contains("Package ID: 1"));
        assertTrue(result.contains("Sydney"));
        assertTrue(result.contains("Melbourne"));
        assertTrue(result.contains("45.0"));
        assertTrue(result.contains("john.doe@example.com"));
    }

    @Test
    public void execute_Should_Throw_When_PackageDoesNotExist() {
        Exception exception = assertThrows(RuntimeException.class, () ->
                command.execute(List.of("99")));

        assertTrue(exception.getMessage().contains("Package with ID 99 not found."));
    }
}