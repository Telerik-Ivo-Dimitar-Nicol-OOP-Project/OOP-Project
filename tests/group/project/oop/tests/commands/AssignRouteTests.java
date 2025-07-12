package group.project.oop.tests.commands;

import core.LogisticRepositoryImpl;
import core.commands.CreateCommand.listing.FindRouteCommand;
import core.commands.creation.AssignRouteCommand;
import core.contracts.Command;
import core.contracts.LogisticRepository;
import exceptions.InvalidUserInputException;
import models.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.DistanceCalculator;

import java.util.ArrayList;
import java.util.List;

public class AssignRouteTests {
    private Command command;
    private LogisticRepository repository;

    @BeforeEach
    public void before() {
        this.repository = new LogisticRepositoryImpl();
        this.command = new AssignRouteCommand(repository);
    }
    @Test
    public void should_throw_exception_when_route_not_suitable(){
        List<String> params = new ArrayList<>(List.of("1", "1"));
        repository.createPackage(Location.SYD, Location.MEL, 23, "Ivo popov 232323232");
        repository.createRoute(Location.MEL, Location.SYD);
        Assertions.assertThrows(InvalidUserInputException.class, ()-> command.execute(params));
    }
    @Test
    public void should_throw_exception_when_package_already_assigned_to_route(){
        List<String> params = new ArrayList<>(List.of("1", "1"));
        repository.createPackage(Location.SYD, Location.MEL, 23, "Ivo popov 232323232");
        repository.createRoute(Location.SYD, Location.MEL);
        repository.getPackageById(1).setAssignedToRoute(true);
        Assertions.assertThrows(InvalidUserInputException.class, ()-> command.execute(params));
    }
    @Test
    public void should_throw_exception_when_package_is_delivered(){
        List<String> params = new ArrayList<>(List.of("1", "1"));
        repository.createPackage(Location.SYD, Location.MEL, 23, "Ivo popov 232323232");
        repository.createRoute(Location.SYD, Location.MEL);
        repository.getPackageById(1).setDelivered();
        Assertions.assertThrows(InvalidUserInputException.class, ()-> command.execute(params));
    }

}
