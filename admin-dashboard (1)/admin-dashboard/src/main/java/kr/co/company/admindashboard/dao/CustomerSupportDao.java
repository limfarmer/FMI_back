package kr.co.company.admindashboard.dao;

import kr.co.company.admindashboard.vo.CustomerSupportVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerSupportDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerSupportDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CustomerSupportVo> findAll() {
        String sql = "SELECT * FROM Customer_Support";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(CustomerSupportVo.class));
    }
}