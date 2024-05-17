package kr.co.company.admindashboard.dao;

import kr.co.company.admindashboard.vo.SearchHistoryVo;
import kr.co.company.admindashboard.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserVo findByUserId(String userId) {
        String sql = "SELECT * FROM Users WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(UserVo.class), userId);
    }

    public List<UserVo> findAll() {
        String sql = "SELECT * FROM Users";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(UserVo.class));
    }

    public List<SearchHistoryVo> findSearchHistoryByUserId(String userId) {
        String sql = "SELECT * FROM Search_History WHERE user_id = ?";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(SearchHistoryVo.class), userId);
    }
}