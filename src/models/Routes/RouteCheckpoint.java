package models.Routes;

import models.Location;

import java.time.LocalDateTime;

public record RouteCheckpoint(Location location, LocalDateTime timestamp, boolean isAvailable) {

}


