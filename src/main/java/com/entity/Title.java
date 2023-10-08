package com.entity;

import java.io.Serializable;

public class Title implements Serializable {
    private static final long serialVersionUID = 4957683856877445307L;

    private Integer titleId;

    private String titleName;

    private String titleState;

    private String titleQuestionone;

    private String titleQuestiontwo;

    private String titleQuestionthree;

    private String titleQuestionfour;
    public Title() {
    }

    public Title(Integer titleId, String titleName, String titleState, String titleQuestionone, String titleQuestiontwo, String titleQuestionthree, String titleQuestionfour) {
        this.titleId = titleId;
        this.titleName = titleName;
        this.titleState = titleState;
        this.titleQuestionone = titleQuestionone;
        this.titleQuestiontwo = titleQuestiontwo;
        this.titleQuestionthree = titleQuestionthree;
        this.titleQuestionfour = titleQuestionfour;
    }

    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getTitleState() {
        return titleState;
    }

    public void setTitleState(String titleState) {
        this.titleState = titleState;
    }

    public String getTitleQuestionone() {
        return titleQuestionone;
    }

    public void setTitleQuestionone(String titleQuestionone) {
        this.titleQuestionone = titleQuestionone;
    }

    public String getTitleQuestiontwo() {
        return titleQuestiontwo;
    }

    public void setTitleQuestiontwo(String titleQuestiontwo) {
        this.titleQuestiontwo = titleQuestiontwo;
    }

    public String getTitleQuestionthree() {
        return titleQuestionthree;
    }

    public void setTitleQuestionthree(String titleQuestionthree) {
        this.titleQuestionthree = titleQuestionthree;
    }

    public String getTitleQuestionfour() {
        return titleQuestionfour;
    }

    public void setTitleQuestionfour(String titleQuestionfour) {
        this.titleQuestionfour = titleQuestionfour;
    }

    @Override
    public String toString() {
        return "Title{" +
                "titleId=" + titleId +
                ", titleName='" + titleName + '\'' +
                ", titleState='" + titleState + '\'' +
                ", titleQuestionone='" + titleQuestionone + '\'' +
                ", titleQuestiontwo='" + titleQuestiontwo + '\'' +
                ", titleQuestionthree='" + titleQuestionthree + '\'' +
                ", titleQuestionfour='" + titleQuestionfour + '\'' +
                '}';
    }
}
