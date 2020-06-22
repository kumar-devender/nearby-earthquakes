package com.smartrecruiters.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartrecruiters.dto.AllEarthquakeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

@RequiredArgsConstructor
@Slf4j
public class FeedEarthquakesRepository implements EarthquakesRepository {
    public static final String USGS_URL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_month.geojson";
    private final ObjectMapper mapper;

    public Optional<AllEarthquakeDTO> getAllMonthEarthquakes() {
        log.info("Getting Earthquakes summary from api");
        try (InputStream inputStream = getEarthquakes()) {
            return of(parse(inputStream));
        } catch (IOException e) {
            log.warn("Failed to get Earthquakes [{}]", e.getCause(), e);
        }
        return empty();
    }

    private AllEarthquakeDTO parse(InputStream inputStream) throws IOException {
        return mapper.readValue(inputStream, AllEarthquakeDTO.class);
    }

    private InputStream getEarthquakes() throws IOException {
        URL url = new URL(USGS_URL);
        /**
         *Different instance of Connection may use same underlying socket connection if we do not call conn.disconnect.
         * In this case The Socket is reused(cached)
         */
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }
        return conn.getInputStream();
    }
}
