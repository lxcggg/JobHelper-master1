package com.service.impl;


import com.entity.Title;

import com.mapper.TitleMapper;
import com.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TitleServiceImpl implements TitleService {
    @Autowired
    private TitleMapper titleMapper;

    @Override
    public int deleteByPrimaryKey(Integer titleId) {
        return titleMapper.deleteByPrimaryKey(titleId);
    }

    @Override
    public int insert(Title record) {
        return titleMapper.insert(record);
    }

    @Override
    public Title selectByPrimaryKey(Integer titleId) {
        return titleMapper.selectByPrimaryKey(titleId);
    }

    @Override
    public List<Title> selectAll() {
        return titleMapper.selectAll();
    }

    @Override
    public List<Title> selectSome() {
        return titleMapper.selectSome();
    }

    @Override
    public int updateByPrimaryKey(Title record) {
        return titleMapper.updateByPrimaryKey(record);
    }

    @Override
    public void batchDelete(List<Integer> ids) {
        titleMapper.batchDelete(ids);
    }

    @Override
    public List<Title> getByCategory(String titleCategory) {
        return titleMapper.getByCategory(titleCategory);
    }

}
