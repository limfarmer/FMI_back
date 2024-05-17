package kr.co.company.admindashboard.controller;

import kr.co.company.admindashboard.dao.AdminDao;
import kr.co.company.admindashboard.vo.AdminVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private AdminDao adminDao;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> loginDetails) {
        String username = loginDetails.get("username");
        String password = loginDetails.get("password");

        Map<String, Object> response = new HashMap<>();
        try {
            AdminVo admin = adminDao.findByAdminId(username);
            if (admin != null && BCrypt.checkpw(password, admin.getPassword())) {
                response.put("success", true);
            } else {
                response.put("success", false);
            }
        } catch (Exception e) {
            response.put("success", false);
        }

        return response;
    }
}
