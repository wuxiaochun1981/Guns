package com.stylefeng.guns.modular.usermanager.service.impl;


import com.stylefeng.guns.common.persistence.dao.UserInfoMapper;
import com.stylefeng.guns.common.persistence.model.UserInfo;
import com.stylefeng.guns.modular.usermanager.service.IUserInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuxiaochun
 * @since 2018-02-28
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {
	
}
