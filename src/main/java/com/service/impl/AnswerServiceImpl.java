package com.service.impl;


import com.entity.Answer;
import com.mapper.AnswerMapper;
import com.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerMapper answerMapper;

    @Override
    public int deleteByPrimaryKey(Integer answerId) {
        return answerMapper.deleteByPrimaryKey(answerId);
    }

    @Override
    public int insert(Answer record) {
        return answerMapper.insert(record);
    }

    @Override
    public Answer selectByPrimaryKey(Integer answerId) {
        return answerMapper.selectByPrimaryKey(answerId);
    }

    @Override
    public List<Answer> selectAll() {
        return answerMapper.selectAll();
    }

    @Override
    public List<Answer> selectSome() {
        return answerMapper.selectSome();
    }

    @Override
    public int updateByPrimaryKey(Answer record) {
        return answerMapper.updateByPrimaryKey(record);
    }

    @Override
    public void batchDelete(List<Integer> ids) {
        answerMapper.batchDelete(ids);
    }

    @Override
    public List<Answer> getByCategory(String answerCategory) {
        return answerMapper.getByCategory(answerCategory);
    }


}
