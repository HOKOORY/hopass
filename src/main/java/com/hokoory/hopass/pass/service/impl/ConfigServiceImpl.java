package com.hokoory.hopass.pass.service.impl;

import com.hokoory.hopass.pass.entity.Config;
import com.hokoory.hopass.pass.mapper.ConfigMapper;
import com.hokoory.hopass.pass.service.IConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hokoory
 * @since 2020-08-11
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {
    @Autowired
    ConfigMapper configMapper;

    @Override
    public Config getOneConfigByKey(HashMap<String, String> map) {
        return configMapper.getOneConfigByKey(map);
    }
}
