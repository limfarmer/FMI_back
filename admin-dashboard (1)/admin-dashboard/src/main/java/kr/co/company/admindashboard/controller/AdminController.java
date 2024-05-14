package kr.co.company.admindashboard.controller;

import kr.co.company.admindashboard.model.Admin;
import kr.co.company.admindashboard.servise.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<Admin> getDashboard() {
        Admin admin = adminService.getDashboardData();
        return ResponseEntity.ok(admin);
    }
}
