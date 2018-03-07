package com.stylefeng.guns.modular.userManager.service.impl;

import com.stylefeng.guns.common.persistence.model.UserInfo;
import com.stylefeng.guns.common.persistence.dao.UserInfoMapper;
import com.stylefeng.guns.modular.userManager.service.IUserInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-03-03
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {
	
}
