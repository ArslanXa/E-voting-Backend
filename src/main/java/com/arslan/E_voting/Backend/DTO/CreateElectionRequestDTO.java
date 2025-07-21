package com.arslan.E_voting.Backend.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateElectionRequestDTO {
    private String title;
    private String description;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Integer eligibilityMinAge;
    private String eligibilityRegion;
}
