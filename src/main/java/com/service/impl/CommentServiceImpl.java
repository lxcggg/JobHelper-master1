package com.service.impl;

import com.entity.Comment;
import com.entity.Company;
import com.entity.Contact;
import com.entity.Job;
import com.mapper.CommentMapper;
import com.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;


    @Override
    public int insert(Comment record) {
        return commentMapper.insert(record);
    }

    @Override
    public List<Comment> getComments(int blogId){
        return commentMapper.getComments(blogId);
    }

//**************************
    @Override
    public int deleteByPrimaryKey(Integer commentId) {
        return commentMapper.deleteByPrimaryKey(commentId);
    }

    @Override
    public Comment selectByPrimaryKey(Integer commentId) {
        return commentMapper.selectByPrimaryKey(commentId);
    }

    @Override
    public List<Comment> getByCategory(String jobCategory) {
        return commentMapper.getByCategory(jobCategory);
    }


    @Override
    public List<Comment> selectAll() {
        return commentMapper.selectAll();
    }
    @Override
    public List<Comment> selectSome() {
        return commentMapper.selectSome();
    }

    @Override
    public void batchDelete(List<Integer> ids) {
        commentMapper.batchDelete(ids);
    }

    @Override
    public int getCommentCount( ){
        return commentMapper.getCommentCount();
    }
}
