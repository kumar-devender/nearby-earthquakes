package com.smartrecruiters.service;

import com.smartrecruiters.dto.AllEarthquakeDTO;
import com.smartrecruiters.dto.Coordinates;
import com.smartrecruiters.dto.EarthquakeDTO;
import com.smartrecruiters.dto.Event;
import com.smartrecruiters.dto.GeometryDTO;
import com.smartrecruiters.repository.EarthquakesRepository;
import com.smartrecruiters.util.DistanceCalculator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
@Slf4j
public class EarthquakesService {
    private final EarthquakesRepository earthquakesRepository;

    public List<Event> getNearByEarthquakes(Coordinates inputCoordinate, int count) {
        log.info("Input coordinates [{}] and count [{}]", inputCoordinate, count);
        return earthquakesRepository.getAllMonthEarthquakes()
                .map(allEarthquake -> filterTopCloseProximityEarthquakes(inputCoordinate, allEarthquake, count))
                .orElseGet(Collections::emptyList);
    }

    private List<Event> filterTopCloseProximityEarthquakes(Coordinates inputCoordinate, AllEarthquakeDTO allEarthquake, int count) {
        List<Event> allEarthquakes = mapToOutputDTO(inputCoordinate, allEarthquake);
        log.info("Total Earthquakes count [{}]", allEarthquakes.size());
        if (allEarthquakes.size() > count) {
            return allEarthquakes.subList(0, count);
        }
        return allEarthquakes;
    }

    private List<Event> mapToOutputDTO(Coordinates inputCoordinate, AllEarthquakeDTO allEarthquake) {
        return allEarthquake.getFeatures().stream()
                .map(earthquakeDTO -> buildOutputDTO(earthquakeDTO, inputCoordinate))
                .sorted(Comparator.comparingDouble(Event::getDistance))
                .collect(Collectors.toList());
    }

    private Event buildOutputDTO(EarthquakeDTO earthquakeDTO, Coordinates inputCoordinate) {
        Coordinates earthquakeCoordinate = buildCoordinates(earthquakeDTO);
        long distance = DistanceCalculator.distance(earthquakeCoordinate, inputCoordinate);
        return Event.builder()
                .title(earthquakeDTO.getProperties().getTitle())
                .distance(distance)
                .build();
    }

    private Coordinates buildCoordinates(EarthquakeDTO earthquakeDTO) {
        List<Double> coordinate = getEarthQuakeCoordinate(earthquakeDTO);
        if (coordinate.size() < 3) {
            throw new IllegalArgumentException(String.format("Malformed coordinates %s", coordinate));
        }

        return Coordinates.builder()
                .longitude(coordinate.get(0))
                .longitude(coordinate.get(1))
                .build();
    }

    private List<Double> getEarthQuakeCoordinate(EarthquakeDTO earthquakeDTO) {
        return ofNullable(earthquakeDTO)
                .map(EarthquakeDTO::getGeometry)
                .map(GeometryDTO::getCoordinates)
                .orElseThrow(() -> new IllegalArgumentException("Could not found coordinate"));
    }
}
