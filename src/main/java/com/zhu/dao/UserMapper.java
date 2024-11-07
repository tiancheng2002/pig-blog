package com.zhu.dao;

import com.zhu.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User getUserByName(@Param("name") String name);
}
