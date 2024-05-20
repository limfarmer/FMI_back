package kr.co.company.admindashboard.controller;

import kr.co.company.admindashboard.dao.AdminDao;
import kr.co.company.admindashboard.vo.AdminVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final AdminDao adminDao;

    @Autowired
    public LoginController(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @PostMapping("/login")
    public ResponseEntity<AdminVo> login(@RequestBody AdminVo adminVo, HttpServletRequest request) {
        System.out.println("Attempting to login with email: " + adminVo.getEmail());
        AdminVo admin = adminDao.findByEmail(adminVo.getEmail());
        if (admin != null && admin.getPassword().equals(adminVo.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("admin", admin); // 세션에 관리자 정보 저장
            System.out.println("Login successful for email: " + adminVo.getEmail());
            return ResponseEntity.ok(admin); // 로그인 성공 시 AdminVo 반환
        } else {
            System.err.println("Login failed for email: " + adminVo.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 인증 실패 시 401 상태 반환
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션 무효화
        }
        return ResponseEntity.ok().build();
    }
}
