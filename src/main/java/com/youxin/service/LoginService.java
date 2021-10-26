package com.youxin.service;

import com.youxin.entities.User;

/**
 * @author youxin
 * @program guojihua
 * @description 登陆
 * @date 2021-10-23 16:53
 */
public interface LoginService {
    public User queryUserByName(String name);
}
