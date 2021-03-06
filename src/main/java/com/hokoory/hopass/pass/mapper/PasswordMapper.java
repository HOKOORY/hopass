package com.hokoory.hopass.pass.mapper;

import com.hokoory.hopass.pass.entity.Password;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hokoory
 * @since 2020-08-21
 */
@Mapper
public interface PasswordMapper extends BaseMapper<Password> {
    public int insertPassword(Password password);
    public List<Password> getPasswordList(String user_id);
    public Password getPasswordDetail(String id);
}
