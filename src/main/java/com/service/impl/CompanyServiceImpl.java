package com.service.impl;


import com.entity.Company;
import com.mapper.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public int deleteByPrimaryKey(Integer companyId) {
        return companyMapper.deleteByPrimaryKey(companyId);
    }

    @Override
    public int insert(Company record) {
        return companyMapper.insert(record);
    }

    @Override
    public Company selectByPrimaryKey(Integer companyId) {
        return companyMapper.selectByPrimaryKey(companyId);
    }

    @Override
    public List<Company> selectAll() {
        return companyMapper.selectAll();
    }

    @Override
    public List<Company> selectSome() {
        return companyMapper.selectSome();
    }

    @Override
    public int updateByPrimaryKey(Company record) {
        return companyMapper.updateByPrimaryKey(record);
    }

    @Override
    public void batchDelete(List<Integer> ids) {
        companyMapper.batchDelete(ids);
    }

    @Override
    public List<Company> getByCategory(String jobCategory) {
        return companyMapper.getByCategory(jobCategory);
    }
}
