package com.mashibing.service;

import com.mashibing.bean.TblUserRecord;

public interface ALoginService {
    public TblUserRecord login(String username, String password);
}
