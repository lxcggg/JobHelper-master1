package com.service.impl;

import com.entity.Company;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyService {
    /**
     * delete by id
     *
     * @param companyId id
     * @return row
     */
    int deleteByPrimaryKey(Integer companyId);

    /**
     * insert into job
     *
     * @param record job
     * @return row
     */
    int insert(Company record);

    /**
     * get job by id
     *
     * @param companyId id
     * @return job
     */
    Company selectByPrimaryKey(Integer companyId);

    /*get all jobs*/
    List<Company> selectAll();

    /*get random 3 jobs*/
    List<Company> selectSome();

    /*update job*/
    int updateByPrimaryKey(Company record);

    void batchDelete(@Param("ids") List<Integer> ids);

    List<Company> getByCategory(String companyCategory);
}
