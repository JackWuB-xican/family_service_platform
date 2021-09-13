package com.mashibing.controller;

import com.alibaba.fastjson.JSONObject;
import com.mashibing.bean.FcEstate;
import com.mashibing.bean.TblCompany;
import com.mashibing.json.Common;
import com.mashibing.service.EstateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@CrossOrigin(origins = "*",allowCredentials="true",allowedHeaders = "*",methods = {})
public class EstateController {

    @Autowired
    private EstateService estateService;

    @RequestMapping("/estate/selectCompany")
    public JSONObject selectCompany(){
        List<TblCompany> tblCompanyNames = estateService.selectCompany();
        return JSONObject.parseObject(JSONObject.toJSONString(new Common(tblCompanyNames)));
    }

    @RequestMapping("/estate/insertEstate")
    public JSONObject insertEstate(FcEstate fcEstate){
        Integer result = estateService.insertEstate(fcEstate);
        if (result==0){
            return JSONObject.parseObject(JSONObject.toJSONString(new Common("房产编码已存在","0")));
        }else{
            return JSONObject.parseObject(JSONObject.toJSONString(new Common("插入房产成功","1")));
        }
    }
}