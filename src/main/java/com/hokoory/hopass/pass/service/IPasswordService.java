package com.hokoory.hopass.pass.service;

import com.hokoory.hopass.pass.entity.Password;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hokoory
 * @since 2020-08-21
 */
public interface IPasswordService extends IService<Password> {
    public int insertPassword(Password password);
    public List<Password> getPasswordList(String user_id);
    public Password getPasswordDetail(String id);
}
