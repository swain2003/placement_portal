package com.placementportal.dto;

import com.placementportal.entity.JobType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class JobRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private JobType type;

    private String location;
    private Double minimumCgpa;
    private Set<String> requiredSkills;
}
