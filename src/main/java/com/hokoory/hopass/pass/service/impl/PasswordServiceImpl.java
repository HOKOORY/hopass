package com.hokoory.hopass.pass.service.impl;

import com.hokoory.hopass.pass.entity.Password;
import com.hokoory.hopass.pass.mapper.PasswordMapper;
import com.hokoory.hopass.pass.service.IPasswordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hokoory
 * @since 2020-08-21
 */
@Service
public class PasswordServiceImpl extends ServiceImpl<PasswordMapper, Password> implements IPasswordService {

    @Autowired
    PasswordMapper passwordMapper;
    @Override
    public int insertPassword(Password password) {
        return passwordMapper.insertPassword(password);
    }

    @Override
    public List<Password> getPasswordList(String user_id) {

        return passwordMapper.getPasswordList(user_id);
    }

    @Override
    public Password getPasswordDetail(String id) {

        return passwordMapper.getPasswordDetail(id);
    }

}
