package com.service.impl;

import com.entity.Comment;
import com.entity.Job;
import com.mapper.CommentMapper;
import com.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;


    @Override
    public int insert(Comment record) {
        return commentMapper.insert(record);
    }
}
