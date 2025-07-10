package group.project.oop.tests.commands;

import models.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.DistanceCalculator;

public class DistanceCalculatorTests {

    @Test
    public void should_return_correct_distance_SYD_MEL(){
        Assertions.assertEquals(877, DistanceCalculator.calculateDistance(Location.SYD, Location.MEL));
    }
    @Test
    public void should_return_correct_distance_SYD_MEL_PER_DAR(){
        Assertions.assertEquals(8411, DistanceCalculator.calculateDistance(Location.SYD, Location.MEL, Location.PER, Location.DAR));
    }
    @Test
    public void should_throw_exception_when_single_location(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> DistanceCalculator.calculateDistance(Location.PER));
    }
}
