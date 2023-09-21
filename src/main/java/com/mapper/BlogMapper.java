package com.mapper;

import com.entity.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogMapper {
    /*delete one blog by id*/
    int deleteByPrimaryKey(Integer blogId);

    /*insert into blog*/
    int insert(Blog record);

    /*get one blog by id*/
    Blog selectByPrimaryKey(Integer blogId);

    /*get all blog*/
    List<Blog> selectAll();

    /*get some blog*/
    List<Blog> selectSome();

    /*update one blog*/
    int updateByPrimaryKey(Blog record);

    void batchDelete(@Param("ids") List<Integer> ids);

    List<Blog> getByBlogCategory(String blogCategory);

    List<Blog> getByBlogCategoryAll(String blogCategory);

    List<Blog> getByLike(String blogLike);

    int getBlogCount( );
}