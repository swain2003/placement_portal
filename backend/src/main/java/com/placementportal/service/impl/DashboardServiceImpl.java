package com.placementportal.service.impl;

import com.placementportal.dto.DashboardResponse;
import com.placementportal.entity.*;
import com.placementportal.exception.ResourceNotFoundException;
import com.placementportal.repository.*;
import com.placementportal.service.DashboardService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final RecruiterProfileRepository recruiterProfileRepository;

    public DashboardServiceImpl(UserRepository userRepository,
                                JobRepository jobRepository,
                                ApplicationRepository applicationRepository,
                                StudentProfileRepository studentProfileRepository,
                                RecruiterProfileRepository recruiterProfileRepository) {
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
        this.applicationRepository = applicationRepository;
        this.studentProfileRepository = studentProfileRepository;
        this.recruiterProfileRepository = recruiterProfileRepository;
    }

    @Override
    public DashboardResponse adminDashboard() {
        return baseDashboard();
    }

    @Override
    public DashboardResponse studentDashboard(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        StudentProfile student = studentProfileRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Student profile not found"));
        long total = applicationRepository.findByStudent(student).size();
        long selected = applicationRepository.findByStudent(student).stream().filter(a -> a.getStatus() == ApplicationStatus.SELECTED).count();
        long rejected = applicationRepository.findByStudent(student).stream().filter(a -> a.getStatus() == ApplicationStatus.REJECTED).count();

        DashboardResponse base = baseDashboard();
        base.setTotalApplications(total);
        base.setSelectedApplications(selected);
        base.setRejectedApplications(rejected);
        return base;
    }

    @Override
    public DashboardResponse recruiterDashboard(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        RecruiterProfile recruiter = recruiterProfileRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Recruiter profile not found"));
        long totalJobs = jobRepository.findByRecruiter(recruiter).size();
        long applications = jobRepository.findByRecruiter(recruiter).stream().mapToLong(job -> applicationRepository.findByJob(job).size()).sum();

        DashboardResponse base = baseDashboard();
        base.setTotalJobs(totalJobs);
        base.setTotalApplications(applications);
        return base;
    }

    private DashboardResponse baseDashboard() {
        Map<String, Long> topSkills = new HashMap<>();
        studentProfileRepository.findAll().forEach(student ->
                student.getSkills().forEach(skill ->
                        topSkills.merge(skill.getName(), 1L, Long::sum)
                )
        );

        return DashboardResponse.builder()
                .totalStudents(userRepository.findByRole(Role.STUDENT).size())
                .totalRecruiters(userRepository.findByRole(Role.RECRUITER).size())
                .totalJobs(jobRepository.count())
                .totalApplications(applicationRepository.count())
                .selectedApplications(applicationRepository.countByStatus(ApplicationStatus.SELECTED))
                .rejectedApplications(applicationRepository.countByStatus(ApplicationStatus.REJECTED))
                .topSkills(topSkills)
                .build();
    }
}
