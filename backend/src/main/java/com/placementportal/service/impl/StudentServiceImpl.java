package com.placementportal.service.impl;

import com.placementportal.dto.ApplicationResponse;
import com.placementportal.dto.StudentProfileRequest;
import com.placementportal.dto.StudentProfileResponse;
import com.placementportal.entity.*;
import com.placementportal.exception.BadRequestException;
import com.placementportal.exception.ResourceNotFoundException;
import com.placementportal.repository.*;
import com.placementportal.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final UserRepository userRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final SkillRepository skillRepository;
    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;

    public StudentServiceImpl(UserRepository userRepository,
                              StudentProfileRepository studentProfileRepository,
                              SkillRepository skillRepository,
                              JobRepository jobRepository,
                              ApplicationRepository applicationRepository) {
        this.userRepository = userRepository;
        this.studentProfileRepository = studentProfileRepository;
        this.skillRepository = skillRepository;
        this.jobRepository = jobRepository;
        this.applicationRepository = applicationRepository;
    }

    @Override
    public StudentProfileResponse getProfile(String email) {
        return ServiceMapper.toStudentResponse(getStudent(email));
    }

    @Override
    @Transactional
    public StudentProfileResponse upsertProfile(String email, StudentProfileRequest request) {
        StudentProfile profile = getStudent(email);
        profile.setBranch(request.getBranch());
        profile.setCgpa(request.getCgpa());
        profile.setGraduationYear(request.getGraduationYear());
        if (request.getSkills() != null) {
            profile.setSkills(resolveSkills(request.getSkills()));
        }
        return ServiceMapper.toStudentResponse(studentProfileRepository.save(profile));
    }

    @Override
    @Transactional
    public StudentProfileResponse uploadResume(String email, String fileName) {
        StudentProfile profile = getStudent(email);
        profile.setResumePath("/uploads/" + fileName);
        return ServiceMapper.toStudentResponse(studentProfileRepository.save(profile));
    }

    @Override
    @Transactional
    public void applyToJob(String email, Long jobId) {
        StudentProfile student = getStudent(email);
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        if (job.getStatus() != JobStatus.OPEN) {
            throw new BadRequestException("Cannot apply to closed jobs");
        }

        if (applicationRepository.findByStudentAndJob(student, job).isPresent()) {
            throw new BadRequestException("Already applied to this job");
        }

        applicationRepository.save(Application.builder().student(student).job(job).status(ApplicationStatus.APPLIED).build());
    }

    @Override
    public List<ApplicationResponse> getApplications(String email) {
        StudentProfile student = getStudent(email);
        return applicationRepository.findByStudent(student).stream()
                .map(ServiceMapper::toApplicationResponse)
                .toList();
    }

    private StudentProfile getStudent(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return studentProfileRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Student profile not found"));
    }

    private Set<Skill> resolveSkills(Set<String> skills) {
        return skills.stream()
                .filter(s -> s != null && !s.isBlank())
                .map(name -> skillRepository.findByNameIgnoreCase(name.trim())
                        .orElseGet(() -> skillRepository.save(Skill.builder().name(name.trim()).build())))
                .collect(Collectors.toSet());
    }
}
