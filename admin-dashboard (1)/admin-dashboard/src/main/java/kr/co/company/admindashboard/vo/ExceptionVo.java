package kr.co.company.admindashboard.vo;

public class ExceptionVo {

    private String message;
    private int statusCode;

    public ExceptionVo(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }
}