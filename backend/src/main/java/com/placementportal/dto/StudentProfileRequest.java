package com.placementportal.dto;

import lombok.Data;

import java.util.Set;

@Data
public class StudentProfileRequest {
    private String branch;
    private Double cgpa;
    private Integer graduationYear;
    private Set<String> skills;
}
