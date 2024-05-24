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

    // 비활성화된 사용자 목록을 가져오는 메소드 추가
    public List<UserVo> findUsersByStatus(String status) {
        String sql = "SELECT * FROM Users WHERE STATUS = ?";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(UserVo.class), status);
    }

    // 비활성화된 사용자 목록을 가져오는 메소드 추가
    public List<UserVo> findDeactivatedUsers() {
        String sql = "SELECT * FROM Users WHERE STATUS = 'DEACTIVATED'";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(UserVo.class));
    }

    // 사용자 삭제 메소드 (기존 메소드 활용)
    public boolean userDelete(String id) {
        int result = 0;
        String sql = "DELETE FROM Users WHERE user_id = ?";
        try {
            jdbcTemplate.update(sql, id);
            result = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result == 1;
    }
}
