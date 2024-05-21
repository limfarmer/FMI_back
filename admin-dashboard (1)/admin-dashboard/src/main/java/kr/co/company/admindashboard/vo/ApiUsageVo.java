package kr.co.company.admindashboard.vo;

import lombok.Getter;

import java.math.BigInteger;
import java.util.List;

@Getter
public class ApiUsageVo {
    private BigInteger totalCalls;
    private BigInteger totalDataUsed;
    private List<Integer> thisMonthData;
    private List<Integer> lastMonthData;
    private List<Integer> twoMonthsAgoData;

    // getters and setters

    public void setTotalCalls(BigInteger totalCalls) {
        this.totalCalls = totalCalls;
    }

    public void setTotalDataUsed(BigInteger totalDataUsed) {
        this.totalDataUsed = totalDataUsed;
    }

    public void setThisMonthData(List<Integer> thisMonthData) {
        this.thisMonthData = thisMonthData;
    }

    public void setLastMonthData(List<Integer> lastMonthData) {
        this.lastMonthData = lastMonthData;
    }

    public void setTwoMonthsAgoData(List<Integer> twoMonthsAgoData) {
        this.twoMonthsAgoData = twoMonthsAgoData;
    }
}
