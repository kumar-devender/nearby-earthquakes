package com.smartrecruiters.service;

import com.smartrecruiters.dto.Event;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class EarthQuakePrinter {
    public void print(List<Event> earthQuakes) {
        earthQuakes.forEach(outputDTO -> log.info("{} || {}",
                outputDTO.getTitle(), outputDTO.getDistance()));
    }
}
