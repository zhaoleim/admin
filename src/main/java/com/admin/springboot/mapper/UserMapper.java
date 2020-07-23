package com.admin.springboot.mapper;

import com.admin.springboot.adminEntity.UserEntity;
import com.admin.springboot.adminVO.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserEntity> getUserList(UserVo user);
}
