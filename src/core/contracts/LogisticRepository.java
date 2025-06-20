package core.contracts;

import models.Location;
import models.PackageImpl;
import models.contracts.Package;

import java.util.List;

public interface LogisticRepository {
    Package createPackage(Location startLocation, Location endLocation, double weight, String contact);
    List<PackageImpl> getPackages();
}
