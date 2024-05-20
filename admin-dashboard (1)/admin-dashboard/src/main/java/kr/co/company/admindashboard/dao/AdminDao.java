package kr.co.company.admindashboard.dao;

import kr.co.company.admindashboard.vo.AdminVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AdminDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public AdminVo findByAdminId(String adminId) {
        String sql = "SELECT * FROM Admin WHERE admin_id = ?";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(AdminVo.class), adminId);
    }

    public AdminVo findByEmail(String email) {
        String sql = "SELECT * FROM Admin WHERE email = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{email}, BeanPropertyRowMapper.newInstance(AdminVo.class));
        } catch (Exception e) {
            // 예외 발생 시 로그 출력
            System.err.println("Error finding admin by email: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void save(AdminVo adminVo) {
        String sql = "INSERT INTO Admin (admin_id, name, email, password) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, adminVo.getAdminId(), adminVo.getName(), adminVo.getEmail(), adminVo.getPassword());
    }

    public void update(AdminVo adminVo) {
        String sql = "UPDATE Admin SET name = ?, email = ?, password = ? WHERE admin_id = ?";
        jdbcTemplate.update(sql, adminVo.getName(), adminVo.getEmail(), adminVo.getPassword(), adminVo.getAdminId());
    }

    public void deleteByAdminId(String adminId) {
        String sql = "DELETE FROM Admin WHERE admin_id = ?";
        jdbcTemplate.update(sql, adminId);
    }

    public List<AdminVo> findAll() {
        String sql = "SELECT * FROM Admin";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(AdminVo.class));
    }
}
