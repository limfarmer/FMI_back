package kr.co.company.admindashboard.dao;

import kr.co.company.admindashboard.vo.ApiLogVo;
import kr.co.company.admindashboard.vo.ApiUsageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApiDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ApiDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ApiUsageVo getApiUsage() {
        String sql = "SELECT COUNT(*) AS totalCalls, SUM(data_used) AS totalDataUsed FROM API_Logs";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(ApiUsageVo.class));
    }

    public List<ApiLogVo> getApiLogs() {
        String sql = "SELECT * FROM API_Logs";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ApiLogVo.class));
    }

}