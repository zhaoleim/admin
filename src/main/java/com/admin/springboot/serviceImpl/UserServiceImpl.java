package com.admin.springboot.serviceImpl;

import com.admin.springboot.adminEntity.UserEntity;
import com.admin.springboot.adminVO.UserVo;
import com.admin.springboot.mapper.UserMapper;
import com.admin.springboot.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 测试用user
 *
 * @author zhaolei
 *
 */
@Service
public class UserServiceImpl implements UserService {

    static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private Gson gson = new GsonBuilder().serializeNulls().create();

    @Resource
    UserMapper userMapper;

    /**
     * 获取列表
     * @param
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public  List<UserEntity> getUserList(UserVo user){
        List<UserEntity> list = userMapper.getUserList(user);
        return list;
    };


}
