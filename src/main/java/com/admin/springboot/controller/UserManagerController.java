package com.admin.springboot.controller;

import com.admin.springboot.adminEntity.UserEntity;
import com.admin.springboot.adminVO.UserVo;
import com.admin.springboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 * @author zhaolei
 */
@RestController
@RequestMapping("/netWork")
public class UserManagerController {

    static Logger logger = LoggerFactory.getLogger(UserManagerController.class);

    @Resource
    private UserService userService;

    @Autowired
    HttpServletRequest httprequest;

    // 获取VPC网络列表
    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    @ResponseBody
    public List<UserEntity> getUserList(@ModelAttribute UserVo user) {
        List<UserEntity> result = null;
        try {
                result = userService.getUserList(user);
        } catch (Exception e) {
            logger.error("<<<getUserList!>>>{}", e);
        }
        return result;
    }
}
