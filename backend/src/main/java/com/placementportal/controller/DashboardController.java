package com.placementportal.controller;

import com.placementportal.dto.ApiResponse;
import com.placementportal.dto.DashboardResponse;
import com.placementportal.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<DashboardResponse>> adminDashboard() {
        return ResponseEntity.ok(ApiResponse.<DashboardResponse>builder().success(true).message("Admin dashboard").data(dashboardService.adminDashboard()).build());
    }

    @GetMapping("/student")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<DashboardResponse>> studentDashboard(Authentication authentication) {
        return ResponseEntity.ok(ApiResponse.<DashboardResponse>builder().success(true).message("Student dashboard").data(dashboardService.studentDashboard(authentication.getName())).build());
    }

    @GetMapping("/recruiter")
    @PreAuthorize("hasRole('RECRUITER')")
    public ResponseEntity<ApiResponse<DashboardResponse>> recruiterDashboard(Authentication authentication) {
        return ResponseEntity.ok(ApiResponse.<DashboardResponse>builder().success(true).message("Recruiter dashboard").data(dashboardService.recruiterDashboard(authentication.getName())).build());
    }
}
