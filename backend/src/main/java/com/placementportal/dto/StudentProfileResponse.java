package com.placementportal.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class StudentProfileResponse {
    private Long id;
    private String fullName;
    private String email;
    private String branch;
    private Double cgpa;
    private Integer graduationYear;
    private String resumePath;
    private Set<String> skills;
}
