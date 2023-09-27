package com.service;


import com.entity.Title;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TitleService {
    /**
     * delete by id
     *
     * @param
     * @return row
     */
    int deleteByPrimaryKey(Integer titleId);

    /**
     * insert into job
     *
     * @param record job
     * @return row
     */
    int insert(Title record);

    /**
     * get job by id
     *
     * @param
     * @return job
     */
    Title selectByPrimaryKey(Integer titleId);

    /*get all jobs*/
    List<Title> selectAll();

    /*get random 3 jobs*/
    List<Title> selectSome();

    /*update job*/
    int updateByPrimaryKey(Title record);

    void batchDelete(@Param("ids") List<Integer> ids);

    List<Title> getByCategory(String titleCategory);
}
