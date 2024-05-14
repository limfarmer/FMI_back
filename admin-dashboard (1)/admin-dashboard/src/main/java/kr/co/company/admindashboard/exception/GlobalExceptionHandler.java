package kr.co.company.admindashboard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // CustomException 처리
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleCustomException(CustomException ex) {
        // CustomException 발생 시, 예외에 설정된 상태 코드와 메시지를 응답으로 반환
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    // 일반 Exception 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        // 일반 예외 발생 시, HTTP 500 Internal Server Error 상태 코드와 에러 메시지를 반환
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error: " + ex.getMessage());
    }

    // 추가적으로 특정 타입의 예외를 더 세밀하게 처리할 수 있습니다. 예를 들어, NullPointerException 처리:
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
        // NullPointerException 발생 시, HTTP 400 Bad Request 상태 코드와 에러 메시지를 반환
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Null value encountered in your request: " + ex.getMessage());
    }

    // 또 다른 예외 처리 예시
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        // IllegalArgumentException 발생 시, HTTP 422 Unprocessable Entity 상태 코드와 에러 메시지를 반환
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Invalid arguments: " + ex.getMessage());
    }
}
