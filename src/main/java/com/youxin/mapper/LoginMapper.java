package com.youxin.mapper;

import com.youxin.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LoginMapper {

    public User queryUserByName(String name);
}
