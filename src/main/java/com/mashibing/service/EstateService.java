package com.mashibing.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mashibing.bean.FcEstate;
import com.mashibing.bean.TblCompany;
import com.mashibing.mapper.FcEstateMapper;
import com.mashibing.mapper.TblCompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstateService {

    @Autowired
    private TblCompanyMapper tblCompanyMapper;

    @Autowired
    private FcEstateMapper fcEstateMapper;

    public List<TblCompany> selectCompany(){
        List<TblCompany> tblCompanies = tblCompanyMapper.selectCompany();
        return tblCompanies;
    }

    /**
     * 在此逻辑中需要先做判断，判断数据库中是否已经存在编码，如果存在，那么对用户发出提示
     * @param fcEstate
     * @return
     */
    public Integer insertEstate(FcEstate fcEstate){
        int result = 0;
        QueryWrapper<FcEstate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("estate_code", fcEstate.getEstateCode());
        FcEstate fe = fcEstateMapper.selectOne(queryWrapper);
        if (fe==null){
            result = fcEstateMapper.insert(fcEstate);
        }
        return result;
    }
}