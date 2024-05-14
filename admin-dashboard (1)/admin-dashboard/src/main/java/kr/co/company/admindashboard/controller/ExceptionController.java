package kr.co.company.admindashboard.controller;

import kr.co.company.admindashboard.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

    @GetMapping("/error/test")
    public ResponseEntity<String> triggerException() {
        throw new CustomException("Custom error occurred", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleCustomException(CustomException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(ex.getMessage());
    }
}
