package models.repository;

import models.Routes.DeliveryRouteImpl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
//TODO after creating the CREATEDELIVERYROUTE Command this will come into play
public class DeliveryRouteRepo {

    private final Map<String, DeliveryRouteImpl> storedRoutes = new HashMap<>();

    public void addRoute(DeliveryRouteImpl route) {
        storedRoutes.put(route.getRouteID(), route);
    }

    public DeliveryRouteImpl getRoute(String routeID) {
        return storedRoutes.get(routeID);
    }
    public boolean contains(String routeId) {
        return storedRoutes.containsKey(routeId);
    }

    public Collection<DeliveryRouteImpl> getAllRoutes() {
        return storedRoutes.values();
    }

    public boolean removeRoute(String routeId) {
        return storedRoutes.remove(routeId) != null;
    }

    public int totalRoutes() {
        return storedRoutes.size();
    }
}
