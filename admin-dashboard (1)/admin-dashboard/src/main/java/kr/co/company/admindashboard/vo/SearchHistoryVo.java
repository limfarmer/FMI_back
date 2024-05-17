package kr.co.company.admindashboard.vo;

import java.sql.Timestamp;

public class SearchHistoryVo {
    private Long searchId;
    private String userId;
    private String searchTerm;
    private Timestamp searchTime;

    public Long getSearchId() {
        return searchId;
    }

    public void setSearchId(Long searchId) {
        this.searchId = searchId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public Timestamp getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(Timestamp searchTime) {
        this.searchTime = searchTime;
    }
}