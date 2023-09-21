package com.entity;

public class Comment {
    private Integer commentId;

    private Integer userId;

    private Integer blogId;

    private String observe;

    public Comment(Integer commentId, Integer userId, Integer blogId, String observe) {
        this.commentId = commentId;
        this.userId = userId;
        this.blogId = blogId;
        this.observe = observe;
    }

    public Comment() {
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getObserve() {
        return observe;
    }

    public void setObserve(String observe) {
        this.observe = observe;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", userId=" + userId +
                ", blogId=" + blogId +
                ", observe='" + observe + '\'' +
                '}';
    }
}
