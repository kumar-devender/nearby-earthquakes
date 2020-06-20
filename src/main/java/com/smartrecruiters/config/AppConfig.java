package com.smartrecruiters.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.smartrecruiters.dto.Coordinates;
import com.smartrecruiters.repository.EarthquakesRepository;
import com.smartrecruiters.repository.FeedEarthquakesRepository;
import com.smartrecruiters.service.EarthQuakeEventWatcher;
import com.smartrecruiters.service.EarthQuakePrinter;
import com.smartrecruiters.service.EarthquakesService;
import com.smartrecruiters.service.IOService;

public class AppConfig {

    public void bootstrap() {
        Coordinates coordinates = new IOService().readInputCoordinate();
        getEarthQuakeEventWatcher().watch(coordinates);
    }

    private EarthQuakeEventWatcher getEarthQuakeEventWatcher() {
        EarthquakesRepository repository = getRepository();
        return new EarthQuakeEventWatcher(getEarthquakesService(repository),
                getEarthQuakePrinter());
    }

    private EarthQuakePrinter getEarthQuakePrinter() {
        return new EarthQuakePrinter();
    }

    private EarthquakesService getEarthquakesService(EarthquakesRepository earthquakesRepository) {
        return new EarthquakesService(earthquakesRepository);
    }

    private EarthquakesRepository getRepository() {
        return new FeedEarthquakesRepository(getMapper());
    }

    private ObjectMapper getMapper() {
        return new JsonMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
