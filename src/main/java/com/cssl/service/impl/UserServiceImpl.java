package com.cssl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cssl.pojo.User;
import com.cssl.service.UserService;
import com.cssl.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author ACER
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-06-11 14:32:35
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




