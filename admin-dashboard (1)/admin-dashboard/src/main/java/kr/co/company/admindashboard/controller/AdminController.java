package kr.co.company.admindashboard.controller;

import kr.co.company.admindashboard.dao.AdminDao;
import kr.co.company.admindashboard.vo.AdminVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminDao adminDao;

    @Autowired
    public AdminController(AdminDao adminDao) {
        this.adminDao = adminDao;
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

    @GetMapping("/dashboard")
    public ResponseEntity<Void> redirectToDashboard() {
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", "/admin/dashboard")
                .build();
    }
}