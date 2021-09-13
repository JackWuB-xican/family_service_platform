package com.mashibing.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mashibing.bean.TblUserRecord;
import com.mashibing.json.Common;
import com.mashibing.json.Permission;
import com.mashibing.json.Permissions;
import com.mashibing.json.UserInfo;
import com.mashibing.service.ALoginService;
import com.mashibing.service.impl.ALoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ALoginController {
     @Autowired
     private ALoginServiceImpl aLoginService;
    @RequestMapping("/auth/login")
    public JSONObject login(String username, String password, HttpSession session){
        TblUserRecord userRecord = aLoginService.login(username,password);
        if(userRecord != null && !"".equals(userRecord)){
            userRecord.setToken(userRecord.getUserName());
        }
        session.setAttribute("userRecord",userRecord);
        Common common = new Common(userRecord);
       return JSONObject.parseObject(JSONObject.toJSONString(common));
    }
    @RequestMapping("/user/info")
    public JSONObject userInfo(HttpSession session){
        //获取用户数据
        TblUserRecord userRecord = (TblUserRecord) session.getAttribute("userRecord");
        //获取对应用户需要账务的功能模块
        String[] rolePrivileges = userRecord.getTblRole().getRolePrivileges().split("-");
        // 拼接需要返回的数据对象的格式
        Permissions permissions = new Permissions();
        List<Permission> permissionList = new ArrayList<>();
        for (String rolePrivilege : rolePrivileges) {
            permissionList.add(new Permission(rolePrivilege));
        }
        permissions.setPermissions(permissionList);
        UserInfo userInfo = new UserInfo(userRecord.getUserName(),permissions);
        Common common = new Common(userInfo);
        return JSONObject.parseObject(JSONObject.toJSONString(common));
    }
    @RequestMapping("/auth/logout")
    public JSONObject loginOut(HttpSession session){
        System.out.println("退出登录");
        session.invalidate();
        return JSONObject.parseObject(JSONObject.toJSONString(new Common(null)));
    }

//    @RequestMapping("/auth/2step-code")
//    public Boolean test(){
//        System.out.println("-----------");
//        return true;
//    }
}
