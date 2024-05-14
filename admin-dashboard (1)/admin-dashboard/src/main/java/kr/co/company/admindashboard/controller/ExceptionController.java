package kr.co.company.admindashboard.controller;

import kr.co.company.admindashboard.exception.CustomException;
import kr.co.company.admindashboard.vo.ExceptionVo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionVo> handleCustomException(CustomException ex) {
        ExceptionVo exceptionVo = new ExceptionVo(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionVo);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionVo> handleException(Exception ex) {
        ExceptionVo exceptionVo = new ExceptionVo("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionVo);
    }
}