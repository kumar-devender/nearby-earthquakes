package com.smartrecruiters.repository;

import com.smartrecruiters.dto.AllEarthquakeDTO;

import java.util.Optional;

public interface EarthquakesRepository {
    Optional<AllEarthquakeDTO> getAllMonthEarthquakes();
}
