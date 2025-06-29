package models.contracts;

import java.time.LocalDateTime;
import java.util.List;

public interface DeliveryRoute extends Printable {

    public String getRouteID();
    public List<String> getLocations();
    LocalDateTime getDepartureTime();
    LocalDateTime getArrivalTime();
    int getTotalStops();
    public int getTotalDistance();

}
