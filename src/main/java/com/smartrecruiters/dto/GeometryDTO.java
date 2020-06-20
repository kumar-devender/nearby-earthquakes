package com.smartrecruiters.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GeometryDTO {
    private List<Double> coordinates;
}
