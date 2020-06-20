package com.smartrecruiters.service;

import com.smartrecruiters.dto.Coordinates;
import lombok.extern.slf4j.Slf4j;

import java.util.Locale;
import java.util.Scanner;

@Slf4j
public class IOService {
    private static final Scanner SCANNER = new Scanner(System.in).useLocale(Locale.US);

    public Coordinates readInputCoordinate() {
        boolean isInvalidCoordinate = true;
        Coordinates coordinates = null;
        do {
            try {
                coordinates = doReadCoordinates();
                isInvalidCoordinate = false;
            } catch (Exception e) {
                log.error("Exception while reading coordinate. Please enter correct input.", e);
                SCANNER.nextLine(); // Consume newline left-over
            }
        } while (isInvalidCoordinate);
        return coordinates;
    }

    private Coordinates doReadCoordinates() {
        double latitude = SCANNER.nextDouble();
        double longitude = SCANNER.nextDouble();
        if (isValidLatitude(latitude) && isValidLongitude(longitude)) {
            return Coordinates.builder()
                    .latitude(latitude)
                    .longitude(longitude)
                    .build();
        } else {
            throw new IllegalArgumentException(String.format("Invalid latitude %s and longitude %s", latitude, longitude));
        }
    }

    private boolean isValidLatitude(double latitude) {
        return latitude <= 90 && latitude >= -90;
    }

    private boolean isValidLongitude(double longitude) {
        return longitude <= 180 && longitude >= -180;
    }
}
