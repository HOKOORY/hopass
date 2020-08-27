package com.hokoory.hopass.pass.mapper;

import com.hokoory.hopass.pass.entity.Config;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hokoory
 * @since 2020-08-11
 */
@Mapper
public interface ConfigMapper extends BaseMapper<Config> {
    Config getOneConfigByKey(HashMap<String,String> map);
}
