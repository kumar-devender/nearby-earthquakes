package com.smartrecruiters.service;

import com.smartrecruiters.dto.Coordinates;
import com.smartrecruiters.dto.Event;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class EarthQuakeEventWatcher {
    private static final int EARTH_QUAKE_COUNT = 10;
    private final EarthquakesService earthquakesService;
    private final EarthQuakePrinter earthQuakePrinter;

    public void watch(Coordinates inputCoordinate) {
        List<Event> earthquakeEvents = earthquakesService.getNearByEarthquakes(inputCoordinate, EARTH_QUAKE_COUNT);
        earthQuakePrinter.print(earthquakeEvents);
    }
}
