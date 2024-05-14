package kr.co.company.admindashboard.controller;

import kr.co.company.admindashboard.servise.ExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exceptions")
public class ExceptionController {
    private final ExceptionService exceptionService;

    @Autowired
    public ExceptionController(ExceptionService exceptionService) {
        this.exceptionService = exceptionService;
    }

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void simulateException() {
        exceptionService.recordException("TestException", "This is a simulated exception.");
    }
}

