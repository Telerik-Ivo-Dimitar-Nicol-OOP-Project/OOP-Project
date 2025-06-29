package core;

import core.contracts.LogisticRepository;
import exceptions.InvalidUserInputException;
import models.Location;
import models.PackageImpl;
import models.Routes.DeliveryRouteImpl;
import models.contracts.DeliveryRoute;
import models.contracts.Package;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LogisticRepositoryImpl implements LogisticRepository {

    public static final String NO_SUCH_PACKAGE_EXISTS = "Package with id: %d does not exist";
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

    @Override
    public Package getPackageById(int id) {
        for (Package packageToGet : packages){
            if (packageToGet.getId() == id){
                return packageToGet;
            }
        }
        throw new InvalidUserInputException(String.format(NO_SUCH_PACKAGE_EXISTS, id));
    }


    // to do
    @Override
    public DeliveryRoute createRoute(Location startLocation, Location endLocation, LocalDateTime departureTime) {
        DeliveryRouteImpl route = new DeliveryRouteImpl(startLocation, endLocation, departureTime);
        this.routes.add(route);
        return route;
    }

    @Override
    public List<DeliveryRouteImpl> getRoutes() {
        return new ArrayList<>(routes);
    }
}
