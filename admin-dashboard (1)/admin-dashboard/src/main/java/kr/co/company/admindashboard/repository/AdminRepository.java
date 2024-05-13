package kr.co.company.admindashboard.repository;

import kr.co.company.admindashboard.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AdminRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AdminRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Admin findFirstByOrderByIdDesc() {
        String sql = "SELECT * FROM admin WHERE ROWNUM = 1 ORDER BY id DESC";
        return jdbcTemplate.queryForObject(sql, new AdminRowMapper());
    }

    private static class AdminRowMapper implements RowMapper<Admin> {
        @Override
        public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
            Admin admin = new Admin();
            admin.setId(rs.getLong("id"));
            admin.setName(rs.getString("name"));
            admin.setEmail(rs.getString("email"));
            return admin;
        }
    }
}
