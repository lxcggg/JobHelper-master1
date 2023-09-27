package com.mapper;

import com.entity.Comment;
import com.entity.Company;
import com.entity.Contact;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {

    int insert(Comment record);

    List<Comment> getComments(int blogId);
//**************************************************
    /*delete one contact by id*/
    int deleteByPrimaryKey(Integer contactId);


    List<Comment> getByCategory(String commentCategory);

    /*get one contact by id*/
    Comment selectByPrimaryKey(Integer commentId);

    /*get all contacts*/
    List<Comment> selectAll();

    List<Comment> selectSome();

    void batchDelete(@Param("ids") List<Integer> ids);

    int getCommentCount( );
}
