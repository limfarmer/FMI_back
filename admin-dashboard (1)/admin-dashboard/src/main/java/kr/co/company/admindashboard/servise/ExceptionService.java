package kr.co.company.admindashboard.service;

import kr.co.company.admindashboard.exception.CustomException;
import org.springframework.stereotype.Service;

@Service
public class ExceptionService {

    public void testCustomException() {
        throw new CustomException("This is a custom exception from service layer.");
    }

    public void testGeneralException() {
        throw new RuntimeException("This is a general exception from service layer.");
    }
}
