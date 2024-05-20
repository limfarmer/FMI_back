package kr.co.company.admindashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseTestRunner implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        try {
            String sql = "SELECT 1 FROM DUAL"; // Oracle DB에서는 DUAL 테이블 사용
            Integer result = jdbcTemplate.queryForObject(sql, Integer.class);
            if (result != null && result == 1) {
                System.out.println("Database connection is successful!");
            }
        } catch (Exception e) {
            System.err.println("Database connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
