package com.smartrecruiters.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Data
public class AllEarthquakeDTO {
    private Set<EarthquakeDTO> features;
}
