package com.stylefeng.guns.modular.personnel.service.impl;

import com.stylefeng.guns.common.persistence.model.PersonnelInfo;
import com.stylefeng.guns.common.persistence.dao.PersonnelInfoMapper;
import com.stylefeng.guns.modular.personnel.service.IPersonnelInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 人员信息 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2017-12-29
 */
@Service
public class PersonnelInfoServiceImpl extends ServiceImpl<PersonnelInfoMapper, PersonnelInfo> implements IPersonnelInfoService {
	
}
