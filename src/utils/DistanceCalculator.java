package utils;

import models.Location;

import java.util.HashMap;

public class DistanceCalculator {
    public static int calculateDistance(Location... locations){
        int distanceToReturn = 0;
        int firstIndex = 0;
        int secondIndex = 1;
        if (locations.length <= 1){
            throw new IllegalArgumentException("More then 2 locations needed to calculate" +
                    " distance");

        }
        // create a hash map with the enum as keys and the matrix index as value
        HashMap<Location, Integer> getIndexesOfMatrix = new HashMap<>();
        getIndexesOfMatrix.put(Location.SYD, 0);
        getIndexesOfMatrix.put(Location.MEL, 1);
        getIndexesOfMatrix.put(Location.ADL, 2);
        getIndexesOfMatrix.put(Location.ASP, 3);
        getIndexesOfMatrix.put(Location.BRI, 4);
        getIndexesOfMatrix.put(Location.DAR, 5);
        getIndexesOfMatrix.put(Location.PER, 6);

//          I have used the full matrix, just because this way it does not matter if its MEL /SYD OR SYD /MEL. It will
//        return the same result.
        final int[][] distanceMatrix = {{0, 877, 1376, 2762, 909, 3935, 4016},
                {877, 0, 725, 2255,1765,3752, 3509},
                {1376, 725, 0, 1530, 1927, 3027, 2785},
                {2726,2255,1530,0,2993,1497,2481},
                {909,1765,1927,2993,0,3426,4311},
                {3935,3725,3027,1497,3426,0,4025},
                {4016, 3509, 2785, 2481, 4311, 4025, 0}};

//        while loop to check all passed Locations to get distance of each consecutive pair, as we do not know how many will be passed.
        while (secondIndex < locations.length){
            distanceToReturn += distanceMatrix[getIndexesOfMatrix.get(locations[firstIndex])][getIndexesOfMatrix.get(locations[secondIndex])];
            firstIndex++;
            secondIndex++;
        }
        return distanceToReturn;
    }
}
