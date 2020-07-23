package com.admin.springboot.service;

import com.admin.springboot.adminEntity.UserEntity;
import com.admin.springboot.adminVO.UserVo;

import java.util.List;

public interface UserService {

    List<UserEntity> getUserList(UserVo user);
}
