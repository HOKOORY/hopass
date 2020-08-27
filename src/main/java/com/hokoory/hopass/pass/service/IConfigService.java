package com.hokoory.hopass.pass.service;

import com.hokoory.hopass.pass.entity.Config;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hokoory
 * @since 2020-08-11
 */
public interface IConfigService extends IService<Config> {
    Config getOneConfigByKey(HashMap<String,String> map);

}
