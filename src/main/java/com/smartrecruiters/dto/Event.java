package com.smartrecruiters.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Event {
    private String title;
    private long distance;
}
