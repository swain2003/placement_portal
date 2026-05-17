package com.placementportal.service.impl;

import com.placementportal.dto.*;
import com.placementportal.entity.*;

import java.util.Set;
import java.util.stream.Collectors;

public final class ServiceMapper {

    private ServiceMapper() {
    }

    public static StudentProfileResponse toStudentResponse(StudentProfile profile) {
        return StudentProfileResponse.builder()
                .id(profile.getId())
                .fullName(profile.getUser().getFullName())
                .email(profile.getUser().getEmail())
                .branch(profile.getBranch())
                .cgpa(profile.getCgpa())
                .graduationYear(profile.getGraduationYear())
                .resumePath(profile.getResumePath())
                .skills(profile.getSkills().stream().map(Skill::getName).collect(Collectors.toSet()))
                .build();
    }

    public static RecruiterProfileResponse toRecruiterResponse(RecruiterProfile profile) {
        return RecruiterProfileResponse.builder()
                .id(profile.getId())
                .fullName(profile.getUser().getFullName())
                .email(profile.getUser().getEmail())
                .companyName(profile.getCompanyName())
                .description(profile.getDescription())
                .website(profile.getWebsite())
                .build();
    }

    public static JobResponse toJobResponse(Job job) {
        Set<String> skills = job.getRequiredSkills().stream().map(Skill::getName).collect(Collectors.toSet());
        return JobResponse.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .type(job.getType())
                .location(job.getLocation())
                .minimumCgpa(job.getMinimumCgpa())
                .status(job.getStatus())
                .createdAt(job.getCreatedAt())
                .companyName(job.getRecruiter().getCompanyName())
                .requiredSkills(skills)
                .build();
    }

    public static ApplicationResponse toApplicationResponse(Application application) {
        StudentProfile student = application.getStudent();
        Job job = application.getJob();
        return ApplicationResponse.builder()
                .id(application.getId())
                .studentName(student.getUser().getFullName())
                .studentEmail(student.getUser().getEmail())
                .jobTitle(job.getTitle())
                .companyName(job.getRecruiter().getCompanyName())
                .status(application.getStatus())
                .createdAt(application.getCreatedAt())
                .resumePath(student.getResumePath())
                .build();
    }
}
