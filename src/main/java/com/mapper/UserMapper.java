package com.mapper;

import com.entity.Collect;
import com.entity.Job;
import com.entity.UJM;
import com.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /*delete one user by id*/
    int deleteByPrimaryKey(Integer userId);

    int deletecollect(Integer jobId);

    /*insert into user*/
    int insert(User record);

    /*get one user by id*/
    User selectByPrimaryKey(Integer userId);

    User getUserJob(Integer userId);

    User getUserCollect(Integer userId);

    int insertUJM (UJM ujm);

    int insertCollect(Collect collect);

    /*get one user by name */
    User verifyUser(String userName);

    /*get all users*/
    List<User> selectAll();



    /*update one user*/
    int updateByPrimaryKey(User record);

    void batchDelete(@Param("ids") List<Integer> ids);

    UJM getUJM(@Param("userId") Integer userId, @Param("jobId") Integer jobId);

    Collect getCollect(@Param("userId") Integer userId, @Param("jobId") Integer jobId);

    int getUserCount();

}