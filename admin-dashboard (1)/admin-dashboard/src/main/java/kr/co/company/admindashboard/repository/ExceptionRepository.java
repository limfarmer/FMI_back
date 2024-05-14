package kr.co.company.admindashboard.repository;

import kr.co.company.admindashboard.model.Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
public class ExceptionRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ExceptionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Exception exception) {
        String sql = "INSERT INTO exceptions (type, message, timestamp) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, exception.getType(), exception.getMessage(), Timestamp.valueOf(exception.getTimestamp()));
    }

    public Exception findById(Long id) {
        String sql = "SELECT * FROM exceptions WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<Exception>() {
            @Override
            public Exception mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Exception(
                        rs.getLong("id"),
                        rs.getString("type"),
                        rs.getString("message"),
                        rs.getTimestamp("timestamp").toLocalDateTime()
                );
            }
        });
    }
}
