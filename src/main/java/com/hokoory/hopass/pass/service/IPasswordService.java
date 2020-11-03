package com.hokoory.hopass.pass.service;

import com.hokoory.hopass.pass.entity.Password;
import com.baomidou.mybatisplus.extension.service.IService;

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
    public Password getPassword(Password password);
}
