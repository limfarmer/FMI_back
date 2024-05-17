package kr.co.company.admindashboard.vo;

import java.sql.Timestamp;

public class ApiLogVo {
    private Long logId;
    private String apiName;
    private Timestamp callTime;
    private Integer responseStatus;
    private Long dataUsed;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public Timestamp getCallTime() {
        return callTime;
    }

    public void setCallTime(Timestamp callTime) {
        this.callTime = callTime;
    }

    public Integer getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Integer responseStatus) {
        this.responseStatus = responseStatus;
    }

    public Long getDataUsed() {
        return dataUsed;
    }

    public void setDataUsed(Long dataUsed) {
        this.dataUsed = dataUsed;
    }
}