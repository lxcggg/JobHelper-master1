package com.entity;

public class Answer {
    private Integer answerId;

    private String answerOne;

    private String answerTwo;

    private String answerThree;

    private String answerFour;

    private String titleName;

    private String userName;

    public Answer() {
    }

    public Answer(Integer answerId, String answerOne, String answerTwo, String answerThree, String answerFour, String titleName, String userName) {
        this.answerId = answerId;
        this.answerOne = answerOne;
        this.answerTwo = answerTwo;
        this.answerThree = answerThree;
        this.answerFour = answerFour;
        this.titleName = titleName;
        this.userName = userName;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getAnswerOne() {
        return answerOne;
    }

    public void setAnswerOne(String answerOne) {
        this.answerOne = answerOne;
    }

    public String getAnswerTwo() {
        return answerTwo;
    }

    public void setAnswerTwo(String answerTwo) {
        this.answerTwo = answerTwo;
    }

    public String getAnswerThree() {
        return answerThree;
    }

    public void setAnswerThree(String answerThree) {
        this.answerThree = answerThree;
    }

    public String getAnswerFour() {
        return answerFour;
    }

    public void setAnswerFour(String answerFour) {
        this.answerFour = answerFour;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerId=" + answerId +
                ", answerOne='" + answerOne + '\'' +
                ", answerTwo='" + answerTwo + '\'' +
                ", answerThree='" + answerThree + '\'' +
                ", answerFour='" + answerFour + '\'' +
                ", titleName='" + titleName + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
