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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3001") // CORS 설정 추가
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
        apiUsage.setThisMonthData(Arrays.asList(10, 20, 30, 40));
        apiUsage.setLastMonthData(Arrays.asList(15, 25, 35, 45));
        apiUsage.setTwoMonthsAgoData(Arrays.asList(5, 15, 25, 35));
        return ResponseEntity.ok(apiUsage);
    }

    @GetMapping("/api-logs")
    public ResponseEntity<List<ApiLogVo>> getApiLogs() {
        List<ApiLogVo> apiLogs = apiDao.getApiLogs();
        return ResponseEntity.ok(apiLogs);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AdminVo> login(@RequestBody AdminVo adminVo, HttpServletRequest request) {
        AdminVo admin = adminDao.findByEmail(adminVo.getEmail());
        if (admin != null && admin.getPassword().equals(adminVo.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("admin", admin);
            return ResponseEntity.ok(admin);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/auth/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/deactivated-users")
    public ResponseEntity<List<UserVo>> getDeactivatedUsers() {
        try {
            List<UserVo> userList = userDao.findDeactivatedUsers();
            return ResponseEntity.ok(userList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/deactivated-users")
    public ResponseEntity<Void> deactivateUser(@RequestBody Map<String, String> payload) {
        try {
            String userId = payload.get("userId");
            if (userId != null && !userId.isEmpty()) {
                userDao.updateUserStatus(userId, "DEACTIVATED");
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        boolean isDeleted = userDao.userDelete(userId);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(500).build();
        }
    }
}
