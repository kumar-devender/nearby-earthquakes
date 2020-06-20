package com.smartrecruiters.service

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.json.JsonMapper
import com.smartrecruiters.dto.AllEarthquakeDTO
import com.smartrecruiters.dto.Coordinates
import com.smartrecruiters.repository.EarthquakesRepository
import spock.lang.Specification

class EarthquakesServiceTest extends Specification {
    private EarthquakesRepository repository = Mock(EarthquakesRepository)
    private EarthquakesService earthquakesService
    private ObjectMapper mapper

    void setup() {
        mapper = new JsonMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        earthquakesService = new EarthquakesService(repository)
    }

    def "test get near by earthquakes when more than ten Earthquake items are available"() {
        given:
        ClassLoader classLoader = ClassLoader.getSystemClassLoader()
        File file = new File(classLoader.getResource("earth-quakes.json").getFile())
        AllEarthquakeDTO allEarthquakeDTO = mapper.readValue(file, AllEarthquakeDTO.class)
        def coordinates = buildCoordinates()
        when:
        def response = earthquakesService.getNearByEarthquakes(coordinates, 10)
        then:
        response.size() == 10
        1 * repository.getAllMonthEarthquakes() >> Optional.of(allEarthquakeDTO)
    }

    def "test get near by earthquakes when less than ten Earthquake items are available"() {
        given:
        ClassLoader classLoader = ClassLoader.getSystemClassLoader()
        File file = new File(classLoader.getResource("2-earth-quakes-item.json").getFile())
        AllEarthquakeDTO allEarthquakeDTO = mapper.readValue(file, AllEarthquakeDTO.class)
        def coordinates = buildCoordinates()
        when:
        def response = earthquakesService.getNearByEarthquakes(coordinates, 10)
        then:
        response.size() == 3
        1 * repository.getAllMonthEarthquakes() >> Optional.of(allEarthquakeDTO)
    }

    def "test get near by earthquakes when coordinate are not present"() {
        given:
        ClassLoader classLoader = ClassLoader.getSystemClassLoader()
        File file = new File(classLoader.getResource("without-coordinate-earth-quakes-item.json").getFile())
        AllEarthquakeDTO allEarthquakeDTO = mapper.readValue(file, AllEarthquakeDTO.class)
        def coordinates = buildCoordinates()
        when:
        earthquakesService.getNearByEarthquakes(coordinates, 10)
        then:
        1 * repository.getAllMonthEarthquakes() >> Optional.of(allEarthquakeDTO)
        IllegalArgumentException ex = thrown()
        ex.message == 'Could not found coordinate'
    }

    def "test get near by earthquakes when coordinate are incorrect"() {
        given:
        ClassLoader classLoader = ClassLoader.getSystemClassLoader()
        File file = new File(classLoader.getResource("malformed-earth-quakes-item.json").getFile())
        AllEarthquakeDTO allEarthquakeDTO = mapper.readValue(file, AllEarthquakeDTO.class)
        def coordinates = buildCoordinates()
        when:
        earthquakesService.getNearByEarthquakes(coordinates, 10)
        then:
        1 * repository.getAllMonthEarthquakes() >> Optional.of(allEarthquakeDTO)
        IllegalArgumentException ex = thrown()
        ex.message.startsWith('Malformed coordinates')
    }

    private static Coordinates buildCoordinates() {
        return Coordinates.builder()
                .latitude(40.730610)
                .longitude(-73.935242)
                .build()
    }
}
