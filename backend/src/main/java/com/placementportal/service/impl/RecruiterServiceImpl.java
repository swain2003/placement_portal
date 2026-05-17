package com.placementportal.service.impl;

import com.placementportal.dto.*;
import com.placementportal.entity.*;
import com.placementportal.exception.BadRequestException;
import com.placementportal.exception.ResourceNotFoundException;
import com.placementportal.repository.*;
import com.placementportal.service.RecruiterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecruiterServiceImpl implements RecruiterService {

    private final UserRepository userRepository;
    private final RecruiterProfileRepository recruiterProfileRepository;
    private final JobRepository jobRepository;
    private final SkillRepository skillRepository;
    private final ApplicationRepository applicationRepository;

    public RecruiterServiceImpl(UserRepository userRepository,
                                RecruiterProfileRepository recruiterProfileRepository,
                                JobRepository jobRepository,
                                SkillRepository skillRepository,
                                ApplicationRepository applicationRepository) {
        this.userRepository = userRepository;
        this.recruiterProfileRepository = recruiterProfileRepository;
        this.jobRepository = jobRepository;
        this.skillRepository = skillRepository;
        this.applicationRepository = applicationRepository;
    }

    @Override
    public RecruiterProfileResponse getProfile(String email) {
        return ServiceMapper.toRecruiterResponse(getRecruiter(email));
    }

    @Override
    @Transactional
    public RecruiterProfileResponse upsertProfile(String email, RecruiterProfileRequest request) {
        RecruiterProfile profile = getRecruiter(email);
        if (request.getCompanyName() != null && !request.getCompanyName().isBlank()) {
            profile.setCompanyName(request.getCompanyName());
        }
        profile.setDescription(request.getDescription());
        profile.setWebsite(request.getWebsite());
        return ServiceMapper.toRecruiterResponse(recruiterProfileRepository.save(profile));
    }

    @Override
    @Transactional
    public JobResponse createJob(String email, JobRequest request) {
        RecruiterProfile recruiter = getRecruiter(email);
        Job job = Job.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .type(request.getType())
                .location(request.getLocation())
                .minimumCgpa(request.getMinimumCgpa())
                .recruiter(recruiter)
                .requiredSkills(resolveSkills(request.getRequiredSkills()))
                .build();
        return ServiceMapper.toJobResponse(jobRepository.save(job));
    }

    @Override
    @Transactional
    public JobResponse updateJob(String email, Long jobId, JobRequest request) {
        RecruiterProfile recruiter = getRecruiter(email);
        Job job = getRecruiterJob(jobId, recruiter);
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setType(request.getType());
        job.setLocation(request.getLocation());
        job.setMinimumCgpa(request.getMinimumCgpa());
        if (request.getRequiredSkills() != null) {
            job.setRequiredSkills(resolveSkills(request.getRequiredSkills()));
        }
        return ServiceMapper.toJobResponse(jobRepository.save(job));
    }

    @Override
    @Transactional
    public void deleteJob(String email, Long jobId) {
        RecruiterProfile recruiter = getRecruiter(email);
        Job job = getRecruiterJob(jobId, recruiter);
        job.setStatus(JobStatus.CLOSED);
        jobRepository.save(job);
    }

    @Override
    public List<JobResponse> myJobs(String email) {
        RecruiterProfile recruiter = getRecruiter(email);
        return jobRepository.findByRecruiter(recruiter).stream()
                .map(ServiceMapper::toJobResponse)
                .toList();
    }

    @Override
    public List<ApplicationResponse> jobApplicants(String email, Long jobId) {
        RecruiterProfile recruiter = getRecruiter(email);
        Job job = getRecruiterJob(jobId, recruiter);
        return applicationRepository.findByJob(job).stream()
                .map(ServiceMapper::toApplicationResponse)
                .toList();
    }

    @Override
    @Transactional
    public ApplicationResponse updateApplicationStatus(String email, Long applicationId, ApplicationStatus status) {
        RecruiterProfile recruiter = getRecruiter(email);
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));

        if (!application.getJob().getRecruiter().getId().equals(recruiter.getId())) {
            throw new BadRequestException("You are not allowed to update this application");
        }

        application.setStatus(status);
        application.setUpdatedAt(LocalDateTime.now());
        return ServiceMapper.toApplicationResponse(applicationRepository.save(application));
    }

    private RecruiterProfile getRecruiter(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return recruiterProfileRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Recruiter profile not found"));
    }

    private Job getRecruiterJob(Long jobId, RecruiterProfile recruiter) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));
        if (!job.getRecruiter().getId().equals(recruiter.getId())) {
            throw new BadRequestException("This job does not belong to you");
        }
        return job;
    }

    private Set<Skill> resolveSkills(Set<String> skills) {
        if (skills == null) {
            return Set.of();
        }
        return skills.stream()
                .filter(s -> s != null && !s.isBlank())
                .map(name -> skillRepository.findByNameIgnoreCase(name.trim())
                        .orElseGet(() -> skillRepository.save(Skill.builder().name(name.trim()).build())))
                .collect(Collectors.toSet());
    }
}
