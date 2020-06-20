package com.smartrecruiters.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import static java.util.Objects.hash;

@NoArgsConstructor
@Data
public class EarthquakeDTO {
    private PropertiesDTO properties;
    private GeometryDTO geometry;

    /**
     * Override so that If two earthquakes happened in exactly the same location get eliminated when added in the set
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EarthquakeDTO)) return false;
        EarthquakeDTO that = (EarthquakeDTO) o;
        return geometry.equals(that.geometry);
    }

    @Override
    public int hashCode() {
        return hash(geometry);
    }
}
