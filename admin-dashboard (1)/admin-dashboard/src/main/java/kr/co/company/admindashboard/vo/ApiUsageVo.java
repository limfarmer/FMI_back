package kr.co.company.admindashboard.vo;

import java.math.BigInteger;

public class ApiUsageVo {
    private BigInteger totalCalls;
    private BigInteger totalDataUsed;

    public BigInteger getTotalCalls() {
        return totalCalls;
    }

    public void setTotalCalls(BigInteger totalCalls) {
        this.totalCalls = totalCalls;
    }

    public BigInteger getTotalDataUsed() {
        return totalDataUsed;
    }

    public void setTotalDataUsed(BigInteger totalDataUsed) {
        this.totalDataUsed = totalDataUsed;
    }
}