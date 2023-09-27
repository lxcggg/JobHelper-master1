package com.entity;

public class Collect {


    private Integer userId;

    private Integer  jobId;

    public Collect() {
    }

    public Collect( Integer userId, Integer jobId) {

        this.userId = userId;
        this.jobId = jobId;
    }



    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    @Override
    public String toString() {
        return "Collect{" +
                ", userId=" + userId +
                ", jobId=" + jobId +
                '}';
    }
}
