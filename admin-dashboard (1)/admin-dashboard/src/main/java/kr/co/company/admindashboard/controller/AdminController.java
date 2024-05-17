package kr.co.company.admindashboard.controller;

import kr.co.company.admindashboard.dao.AdminDao;
import kr.co.company.admindashboard.dao.ApiDao;
import kr.co.company.admindashboard.dao.CustomerSupportDao;
import kr.co.company.admindashboard.dao.UserDao;
import kr.co.company.admindashboard.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminDao adminDao;
    private final UserDao userDao;
    private final ApiDao apiDao;
    private final CustomerSupportDao customerSupportDao;

    @Autowired
    public AdminController(AdminDao adminDao, UserDao userDao, ApiDao apiDao, CustomerSupportDao customerSupportDao) {
        this.adminDao = adminDao;
        this.userDao = userDao;
        this.apiDao = apiDao;
        this.customerSupportDao = customerSupportDao;
    }

    @GetMapping("/{adminId}")
    public ResponseEntity<AdminVo> getAdminById(@PathVariable String adminId) {
        AdminVo adminVo = adminDao.findByAdminId(adminId);
        if (adminVo != null) {
            return ResponseEntity.ok(adminVo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createAdmin(@RequestBody AdminVo adminVo) {
        adminDao.save(adminVo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{adminId}")
    public ResponseEntity<Void> updateAdmin(@PathVariable String adminId, @RequestBody AdminVo adminVo) {
        adminVo.setAdminId(adminId);
        adminDao.update(adminVo);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{adminId}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable String adminId) {
        adminDao.deleteByAdminId(adminId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AdminVo>> getAllAdmins() {
        List<AdminVo> adminList = adminDao.findAll();
        return ResponseEntity.ok(adminList);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserVo>> getAllUsers() {
        List<UserVo> userList = userDao.findAll();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserVo> getUserById(@PathVariable String userId) {
        UserVo userVo = userDao.findByUserId(userId);
        if (userVo != null) {
            return ResponseEntity.ok(userVo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/users/search-history/{userId}")
    public ResponseEntity<List<SearchHistoryVo>> getUserSearchHistory(@PathVariable String userId) {
        List<SearchHistoryVo> searchHistory = userDao.findSearchHistoryByUserId(userId);
        return ResponseEntity.ok(searchHistory);
    }

    @GetMapping("/customer-support")
    public ResponseEntity<List<CustomerSupportVo>> getAllCustomerSupportRequests() {
        List<CustomerSupportVo> customerSupportList = customerSupportDao.findAll();
        return ResponseEntity.ok(customerSupportList);
    }

    @GetMapping("/api-usage")
    public ResponseEntity<ApiUsageVo> getApiUsage() {
        ApiUsageVo apiUsage = apiDao.getApiUsage();
        return ResponseEntity.ok(apiUsage);
    }

    @GetMapping("/api-logs")
    public ResponseEntity<List<ApiLogVo>> getApiLogs() {
        List<ApiLogVo> apiLogs = apiDao.getApiLogs();
        return ResponseEntity.ok(apiLogs);
    }
}