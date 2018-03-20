package com.project.manager.ui.components;

import com.project.manager.controllers.AdminDashboardController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminDashboardTablesComponent {

    private AdminDashboardController adminDashboardController;

    @Autowired
    public AdminDashboardTablesComponent(AdminDashboardController adminDashboardController) {
        this.adminDashboardController = adminDashboardController;
    }

    public void generateProjectTableView() {
//        adminDashboardController.getProjectTable()
    }
}
