package com.placementportal.config;

import com.placementportal.entity.*;
import com.placementportal.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seedData(UserRepository userRepository,
                               StudentProfileRepository studentProfileRepository,
                               RecruiterProfileRepository recruiterProfileRepository,
                               SkillRepository skillRepository,
                               JobRepository jobRepository,
                               PasswordEncoder passwordEncoder) {
        return args -> {
            Skill java = skillRepository.findByNameIgnoreCase("Java")
                    .orElseGet(() -> skillRepository.save(Skill.builder().name("Java").build()));
            Skill react = skillRepository.findByNameIgnoreCase("React")
                    .orElseGet(() -> skillRepository.save(Skill.builder().name("React").build()));

            if (userRepository.findByEmail("admin@portal.com").isEmpty()) {
                userRepository.save(User.builder()
                        .fullName("Portal Admin")
                        .email("admin@portal.com")
                        .password(passwordEncoder.encode("Admin@123"))
                        .role(Role.ADMIN)
                        .build());
            }

            User recruiterUser = userRepository.findByEmail("recruiter@company.com").orElseGet(() ->
                    userRepository.save(User.builder()
                            .fullName("Tech Recruiter")
                            .email("recruiter@company.com")
                            .password(passwordEncoder.encode("Recruiter@123"))
                            .role(Role.RECRUITER)
                            .build())
            );

            RecruiterProfile recruiter = recruiterProfileRepository.findByUser(recruiterUser).orElseGet(() ->
                    recruiterProfileRepository.save(RecruiterProfile.builder()
                            .user(recruiterUser)
                            .companyName("TechNova")
                            .description("Product engineering company")
                            .website("https://technova.example")
                            .build())
            );

            User studentUser = userRepository.findByEmail("student@college.com").orElseGet(() ->
                    userRepository.save(User.builder()
                            .fullName("Demo Student")
                            .email("student@college.com")
                            .password(passwordEncoder.encode("Student@123"))
                            .role(Role.STUDENT)
                            .build())
            );

            studentProfileRepository.findByUser(studentUser).orElseGet(() ->
                    studentProfileRepository.save(StudentProfile.builder()
                            .user(studentUser)
                            .branch("CSE")
                            .cgpa(8.5)
                            .graduationYear(2027)
                            .skills(Set.of(java, react))
                            .build())
            );

            if (jobRepository.count() == 0) {
                jobRepository.save(Job.builder()
                        .title("Java Backend Intern")
                        .description("Work on Spring Boot microservices and APIs")
                        .type(JobType.INTERNSHIP)
                        .location("Bengaluru")
                        .minimumCgpa(7.0)
                        .recruiter(recruiter)
                        .requiredSkills(Set.of(java))
                        .build());
            }
        };
    }
}
