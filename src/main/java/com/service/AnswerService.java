package com.service;


import com.entity.Answer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnswerService {
    /**
     * delete by id
     *
     * @param
     * @return row
     */
    int deleteByPrimaryKey(Integer answerId);

    /**
     * insert into job
     *
     * @param record job
     * @return row
     */
    int insert(Answer record);

    /**
     * get job by id
     *
     * @param
     * @return job
     */
    Answer selectByPrimaryKey(Integer answerId);

    /*get all jobs*/
    List<Answer> selectAll();

    /*get random 3 jobs*/
    List<Answer> selectSome();

    /*update job*/
    int updateByPrimaryKey(Answer record);

    void batchDelete(@Param("ids") List<Integer> ids);

    List<Answer> getByCategory(String answerCategory);
}
