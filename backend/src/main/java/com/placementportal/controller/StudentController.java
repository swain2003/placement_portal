package com.placementportal.controller;

import com.placementportal.dto.*;
import com.placementportal.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/students")
@PreAuthorize("hasRole('STUDENT')")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<StudentProfileResponse>> getProfile(Authentication authentication) {
        return ResponseEntity.ok(ApiResponse.<StudentProfileResponse>builder().success(true).message("Profile fetched").data(studentService.getProfile(authentication.getName())).build());
    }

    @PutMapping("/profile")
    public ResponseEntity<ApiResponse<StudentProfileResponse>> updateProfile(Authentication authentication,
                                                                             @RequestBody StudentProfileRequest request) {
        return ResponseEntity.ok(ApiResponse.<StudentProfileResponse>builder().success(true).message("Profile updated").data(studentService.upsertProfile(authentication.getName(), request)).build());
    }

    @PostMapping("/resume")
    public ResponseEntity<ApiResponse<StudentProfileResponse>> uploadResume(Authentication authentication,
                                                                            @RequestParam("file") MultipartFile file) throws IOException {
        String original = file.getOriginalFilename() == null ? "resume.pdf" : file.getOriginalFilename();
        String ext = original.contains(".") ? original.substring(original.lastIndexOf('.')) : ".pdf";
        String fileName = UUID.randomUUID() + ext;
        Path uploadDir = Paths.get("uploads");
        Files.createDirectories(uploadDir);
        Files.write(uploadDir.resolve(fileName), file.getBytes());
        return ResponseEntity.ok(ApiResponse.<StudentProfileResponse>builder().success(true).message("Resume uploaded").data(studentService.uploadResume(authentication.getName(), fileName)).build());
    }

    @PostMapping("/jobs/{jobId}/apply")
    public ResponseEntity<ApiResponse<Void>> apply(Authentication authentication, @PathVariable Long jobId) {
        studentService.applyToJob(authentication.getName(), jobId);
        return ResponseEntity.ok(ApiResponse.<Void>builder().success(true).message("Applied successfully").build());
    }

    @GetMapping("/applications")
    public ResponseEntity<ApiResponse<List<ApplicationResponse>>> myApplications(Authentication authentication) {
        return ResponseEntity.ok(ApiResponse.<List<ApplicationResponse>>builder().success(true).message("Applications fetched").data(studentService.getApplications(authentication.getName())).build());
    }
}
