package com.placementportal.service;

import com.placementportal.dto.DashboardResponse;

public interface DashboardService {
    DashboardResponse adminDashboard();

    DashboardResponse studentDashboard(String email);

    DashboardResponse recruiterDashboard(String email);
}
