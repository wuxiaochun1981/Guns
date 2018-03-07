package com.stylefeng.guns.rest.common.persistence.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.rest.common.persistence.model.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author wuxiaochun
 * @since 2018-02-28
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    Integer updateQueryCount(Map<String,Object> parameter);
}