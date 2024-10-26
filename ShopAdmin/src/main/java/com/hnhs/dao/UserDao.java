package com.hnhs.dao;

import com.hnhs.info.RegisterInfo;
import com.hnhs.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {
    int register(RegisterInfo registerInfo);

    User login(@Param("tel") String tel, @Param("password") String password);
    @Select("select * from 202_user where tel=#{tel}")
    List<User> selectUserTel(String tel);

}
