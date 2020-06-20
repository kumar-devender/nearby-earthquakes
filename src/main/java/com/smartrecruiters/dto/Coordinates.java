package com.smartrecruiters.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Coordinates {
    private double latitude;
    private double longitude;
    /**
     * altitude in meters
     */
    private double depth;

}
