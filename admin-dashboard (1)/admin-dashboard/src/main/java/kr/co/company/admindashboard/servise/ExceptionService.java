package kr.co.company.admindashboard.servise;

import kr.co.company.admindashboard.model.Exception;
import kr.co.company.admindashboard.repository.ExceptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ExceptionService {
    private final ExceptionRepository exceptionRepository;

    @Autowired
    public ExceptionService(ExceptionRepository exceptionRepository) {
        this.exceptionRepository = exceptionRepository;
    }

    public void recordException(String type, String message) {
        Exception exception = new Exception();
        exception.setType(type);
        exception.setMessage(message);
        exception.setTimestamp(LocalDateTime.now());
        exceptionRepository.save(exception);
    }
}
