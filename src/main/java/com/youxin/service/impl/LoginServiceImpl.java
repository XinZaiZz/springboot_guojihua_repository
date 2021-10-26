package com.youxin.service.impl;

import com.youxin.entities.User;
import com.youxin.mapper.LoginMapper;
import com.youxin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author youxin
 * @program guojihua
 * @description LoginServiceImpl
 * @date 2021-10-23 16:53
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginMapper loginMapper;
    @Override
    public User queryUserByName(String name) {
        return loginMapper.queryUserByName(name);
    }
}
