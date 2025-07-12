//package group.project.oop.tests.commands;
//
//import core.LogisticRepositoryImpl;
//import core.commands.creation.AssignRouteCommand;
//import models.Location;
//import models.PackageImpl;
//import models.Routes.DeliveryRouteImpl;
//import models.contracts.DeliveryRoute;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class AssignRouteCommandTests {
//
//    private LogisticRepositoryImpl repository;
//    private AssignRouteCommand command;
//
//    @BeforeEach
//    public void setup() {
//        repository = new LogisticRepositoryImpl();
//        command = new AssignRouteCommand(repository);
//    }
//
//    @Test
//    public void execute_Should_SetExpectedArrivalTime_When_PackageIsAssignedToMatchingRoute() {
//        // Locations
//        Location sydney = new Location("Sydney");
//        Location melbourne = new Location("Melbourne");
//
//        // Create route with Sydney -> Melbourne
//        int routeId = 1;
//        DeliveryRouteImpl route = new DeliveryRouteImpl(routeId, sydney, LocalDateTime.of(2025, 7, 13, 6, 0));
//        route.addCheckpoint(melbourne, LocalDateTime.of(2025, 7, 13, 20, 0));
//        repository.getAllRoutes().add(route);
//
//        // Create package Sydney -> Melbourne
//        PackageImpl pack = new PackageImpl(1, sydney, melbourne, 50, "Customer A");
//        repository.getAllPackages().add(pack);
//
//        // Assign route to package
//        String result = command.execute(List.of("1", "1"));
//
//        // Check that expectedArrival is set correctly
//        assertEquals(LocalDateTime.of(2025, 7, 13, 20, 0), pack.getExpectedArrival());
//        assertTrue(result.contains("Package with id 1 was added to Route 1"));
//    }
//}
