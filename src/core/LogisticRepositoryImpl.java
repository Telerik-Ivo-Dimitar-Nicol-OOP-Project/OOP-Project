package core;

import core.contracts.LogisticRepository;
import models.Location;
import models.PackageImpl;
import models.Routes.DeliveryRouteImpl;
import models.contracts.DeliveryRoute;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LogisticRepositoryImpl implements LogisticRepository {
    private int nextPackageId;

    private final List<PackageImpl> packages = new ArrayList<>();
    private final List<DeliveryRouteImpl> routes = new ArrayList<>();

    public LogisticRepositoryImpl(){
        nextPackageId = 0;

    }

    @Override
    public PackageImpl createPackage(Location startLocation, Location endLocation, double weight, String contact) {
        PackageImpl newPackage = new PackageImpl(++nextPackageId, startLocation, endLocation, weight, contact );
        this.packages.add(newPackage);
        return newPackage;
    }

    @Override
    public List<PackageImpl> getPackages() {
        return new ArrayList<>(packages);
    }
// to do
    @Override
    public DeliveryRoute createRoute(Location startLocation, Location endLocation, LocalDateTime departureTime) {
        return null;
    }

    @Override
    public List<DeliveryRouteImpl> getRoutes() {
        return new ArrayList<>(routes);
    }
}
