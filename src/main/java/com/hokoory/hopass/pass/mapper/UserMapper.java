package com.hokoory.hopass.pass.mapper;

import com.hokoory.hopass.pass.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hokoory.hopass.pass.entity.UserToken;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hokoory
 * @since 2020-08-05
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    UserToken getUserByUsername(Map<String,String> map);
    int updateUserBySignUp(User user);
    int insertUserBySignUp(User user);
    User getUserInPass(Map<String,String> map);
}
