package kr.co.company.admindashboard.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Exception {
    private Long id;
    private String type;
    private String message;
    private LocalDateTime timestamp;

    public Exception() {}

    public Exception(Long id, String type, String message, LocalDateTime timestamp) {
        this.id = id;
        this.type = type;
        this.message = message;
        this.timestamp = timestamp;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
