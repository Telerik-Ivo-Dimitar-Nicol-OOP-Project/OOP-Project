package core.contracts;

import models.Location;
import models.PackageImpl;
import models.Routes.DeliveryRouteImpl;
import models.contracts.DeliveryRoute;
import models.contracts.Package;

import java.time.LocalDateTime;
import java.util.List;

public interface LogisticRepository {
    Package createPackage(Location startLocation, Location endLocation, double weight, String contact);
    List<PackageImpl> getPackages();

    DeliveryRoute createRoute(Location startLocation, Location endLocation, LocalDateTime departureTime);
    List<DeliveryRouteImpl> getRoutes();
}
