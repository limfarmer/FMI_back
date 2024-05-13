package kr.co.company.admindashboard.servise;

import kr.co.company.admindashboard.model.Admin;
import kr.co.company.admindashboard.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin getDashboardData() {
        return adminRepository.findFirstByOrderByIdDesc();
    }
}
