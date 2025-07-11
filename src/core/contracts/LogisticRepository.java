package core.contracts;

import models.Location;
import models.PackageImpl;
import models.Routes.DeliveryRouteImpl;
import models.contracts.DeliveryRoute;
import models.contracts.Package;
import models.contracts.Vehicle;

import java.time.LocalDateTime;
import java.util.List;

public interface LogisticRepository {
    Package createPackage(Location startLocation, Location endLocation, double weight, String contact);
    List<PackageImpl> getPackages();
    Package getPackageById(int id);

    DeliveryRoute createRoute(Location startLocation, Location endLocation);
    List<DeliveryRouteImpl> getRoutes();
    List<Vehicle> getAllVehicles();
    DeliveryRoute getRouteById(int id);
    Vehicle getVehicleById(int id);
}
