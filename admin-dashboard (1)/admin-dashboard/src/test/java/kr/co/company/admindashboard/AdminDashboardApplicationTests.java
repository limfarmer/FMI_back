package kr.co.company.admindashboard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AdminDashboardApplicationTests {

	@Autowired
	private ExceptionController exceptionController;

	@Test
	public void testCustomExceptionHandling() {
		ResponseEntity<String> response = exceptionController.triggerException();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(response.getBody()).contains("Custom error occurred");
	}
}
