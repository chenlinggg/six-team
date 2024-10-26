package com.hnhs.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.hnhs.dao.UserDao;
import com.hnhs.exception.MyException;
import com.hnhs.info.LoginInfo;
import com.hnhs.info.RegisterInfo;
import com.hnhs.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Value("${token.key}")
    private  String tokenkey;

    public Integer register(RegisterInfo registerInfo){
        //判断手机号有没有注册过
        String tel=registerInfo.getTel();
        List<User> users = userDao.selectUserTel(tel);
        if(users.size() == 0){
            return userDao.register(registerInfo);
        }
        return userDao.register(registerInfo);
    }

    public String login(LoginInfo loginInfo){
        System.out.println("tokenkey=="+tokenkey);
        User user = userDao.login(loginInfo.getTel(),loginInfo.getPassword());
        if(ObjectUtil.isEmpty(user)){
            throw new MyException(502,"手机号或密码错误");
        }
        //验证token 可以参考hutool里面的内容
        //参考jwt+token的模式
        Map<String,Object> map=new HashMap<>();
        map.put("id",user.getId());
        //截取手机号 取前四 中**** 尾数
        map.put("tel",user.getTel().substring(0,4)+"****"+user.getTel().substring(8));
        //设置过期时间 2天时间
        map.put(JWT.EXPIRES_AT,System.currentTimeMillis()+2*24*60*60*1000);
        String token= JWTUtil.createToken(map,tokenkey.getBytes());
        return token;
    }
}
