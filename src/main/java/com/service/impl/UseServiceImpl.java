package com.service.impl;

import com.entity.Collect;
import com.entity.UJM;
import com.entity.User;
import com.mapper.UserMapper;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UseServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(Integer userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int deletecollect(Integer jobId){
        return userMapper.deletecollect(jobId);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public User selectByPrimaryKey(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int insertUJM(UJM ujm) {
        return userMapper.insertUJM(ujm);
    }

    @Override
    public int insertCollect(Collect collect) {
        return userMapper.insertCollect(collect);
    }

    @Override
    public User getUserJob(Integer userId) {
        return userMapper.getUserJob(userId);
    }

    @Override
    public User getUserCollect(Integer userId) {
        return userMapper.getUserCollect(userId);
    }

    @Override
    public User verifyUser(String userName) {
        return userMapper.verifyUser(userName);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }


    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public void batchDelete(List<Integer> ids) {
        userMapper.batchDelete(ids);
    }

    @Override
    public UJM getUJM(Integer userId, Integer jobId) {
        return userMapper.getUJM(userId,jobId);
    }
    @Override
    public Collect getCollect(Integer userId, Integer jobId) {
        return userMapper.getCollect(userId,jobId);
    }

    @Override
    public int getUserCount( ){
        return userMapper.getUserCount();
    }
}
