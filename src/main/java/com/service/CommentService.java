package com.service;

import com.entity.Comment;

import com.entity.Company;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentService {
    int insert(Comment record);

    List<Comment> getComments(int blogId);

//    *********************************************
    /*delete one contact by id*/
    int deleteByPrimaryKey(Integer commentId);

    List<Comment> getByCategory(String commentCategory);

    Comment selectByPrimaryKey(Integer commentId);

    /*get all contacts*/
    List<Comment> selectAll();
    List<Comment> selectSome();



    void batchDelete(@Param("ids") List<Integer> ids);

    int getCommentCount( );
}
