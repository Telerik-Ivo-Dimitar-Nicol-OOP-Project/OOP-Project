package utils;

import core.commands.CommandConstants;
import models.PackageImpl;

import java.util.ArrayList;
import java.util.List;

public class ListingHelpers {
    public static String packagesToString(List<PackageImpl> packages){
        List<String> result = new ArrayList<>();
        for (PackageImpl pack : packages){
            result.add(pack.toString());
        }
        return String.join(CommandConstants.JOIN_DELIMITER + System.lineSeparator(), result).trim();
    }

}
