package com.placementportal.repository;

import com.placementportal.entity.Application;
import com.placementportal.entity.Job;
import com.placementportal.entity.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByStudent(StudentProfile student);

    List<Application> findByJob(Job job);

    Optional<Application> findByStudentAndJob(StudentProfile student, Job job);

    long countByStatus(com.placementportal.entity.ApplicationStatus status);
}
