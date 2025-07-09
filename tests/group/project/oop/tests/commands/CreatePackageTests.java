package group.project.oop.tests.commands;

import core.LogisticRepositoryImpl;
import core.commands.CreateCommand.listing.FindRouteCommand;
import core.contracts.Command;
import core.contracts.LogisticRepository;
import exceptions.InvalidUserInputException;
import models.Location;
import models.PackageImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreatePackageTests {
    private Command command;
    private LogisticRepository repository;

    @BeforeEach
    public void before() {
        this.repository = new LogisticRepositoryImpl();
        this.command = new FindRouteCommand(repository);
    }

    @Test
    public void constructor_Should_ThrowException_When_Weight_OutOf_Bounds() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PackageImpl(1, Location.SYD, Location.BRI, 0.05, "Test contact info"));
    }
    @Test
    public void constructor_Should_ThrowException_When_Contact_outOf_Bounds() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PackageImpl(1, Location.SYD, Location.BRI, 23, "Test"));
    }
    @Test
    public void package_should_be_added_to_repo() {
        repository.createPackage(Location.SYD, Location.BRI, 23, "Test contact info");
        Assertions.assertEquals(1, repository.getPackages().size());
    }
    @Test
    public void package_should_have_incremented_id() {
        repository.createPackage(Location.SYD, Location.BRI, 23, "Test contact info");
        repository.createPackage(Location.SYD, Location.BRI, 23, "Test contact info");
        Assertions.assertEquals(2, repository.getPackages().get(1).getId());
    }
    @Test
    public void package_should_init_assigned_route_false() {
        repository.createPackage(Location.SYD, Location.BRI, 23, "Test contact info");
        Assertions.assertEquals(false, repository.getPackages().get(0).isAssignedToRoute());
    }
}
