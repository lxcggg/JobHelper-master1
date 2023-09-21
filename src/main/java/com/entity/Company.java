package com.entity;

import java.util.List;

public class Company {
    private Integer companyId;

    private String companyName;

    private List<Job> list;

    public Company(Integer companyId, String companyName, List<Job> list) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.list = list;
    }

    public Company() {
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Job> getList() {
        return list;
    }

    public void setList(List<Job> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", list=" + list +
                '}';
    }
}
