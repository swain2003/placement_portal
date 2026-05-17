package com.placementportal.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecruiterProfileResponse {
    private Long id;
    private String fullName;
    private String email;
    private String companyName;
    private String description;
    private String website;
}
