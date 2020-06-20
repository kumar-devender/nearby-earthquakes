package com.smartrecruiters.util;

import com.smartrecruiters.dto.Coordinates;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DistanceCalculator {
    /**
     * https://stackoverflow.com/questions/3694380/calculating-distance-between-two-points-using-latitude-longitude
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     * <p>
     * coordinate1 Start point. coordinate2 End point el1
     *
     * @returns Distance in KiloMeters
     */
    public long distance(@NonNull Coordinates coordinate1,
                         @NonNull Coordinates coordinate2) {
        final int R = 6371; // Radius of the earth
        double latDistance = Math.toRadians(coordinate2.getLatitude() - coordinate1.getLatitude());
        double lonDistance = Math.toRadians(coordinate2.getLongitude() - coordinate1.getLongitude());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(coordinate1.getLatitude())) * Math.cos(Math.toRadians(coordinate2.getLatitude()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = coordinate1.getDepth() - coordinate2.getDepth();

        distance = Math.pow(distance, 2) + Math.pow(height, 2);
        return Math.round(Math.sqrt(distance) / 1000);
    }
}
