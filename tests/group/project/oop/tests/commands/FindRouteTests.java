package group.project.oop.tests.commands;

import core.LogisticRepositoryImpl;
import core.commands.CreateCommand.listing.FindRouteCommand;
import core.contracts.Command;
import core.contracts.LogisticRepository;
import exceptions.InvalidUserInputException;
import models.Location;
import models.Routes.DeliveryRouteImpl;
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
    @Test
    public void shouldThrowException_When_NoRoutes_Exist() {
        List<String> params = new ArrayList<>(List.of("1"));

        Assertions.assertThrows(InvalidUserInputException.class, () -> command.execute(params));
    }
    @Test
    public void shouldThrowException_When_Package_is_Delivered() {
        repository.createPackage(Location.SYD, Location.MEL, 22,"Ivo popov 9292929292");
        repository.getPackageById(1).setDelivered();
        repository.createRoute(Location.SYD, Location.MEL);
        List<String> params = new ArrayList<>(List.of("1"));

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }
    @Test
    public void shouldThrowException_When_Package_is_Assigned_toRoute() {
        repository.createPackage(Location.SYD, Location.MEL, 22,"Ivo popov 9292929292");
        repository.getPackageById(1).setAssignedToRoute(true);
        repository.createRoute(Location.SYD, Location.MEL);
        List<String> params = new ArrayList<>(List.of("1"));

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }
    @Test
    public void shouldThrowException_When_No_SuitableRoute_found() {
        repository.createPackage(Location.SYD, Location.MEL, 22,"Ivo popov 9292929292");
        repository.createRoute(Location.MEL, Location.SYD);
        List<String> params = new ArrayList<>(List.of("1"));

        Assertions.assertThrows(InvalidUserInputException.class, () -> command.execute(params));
    }

}
