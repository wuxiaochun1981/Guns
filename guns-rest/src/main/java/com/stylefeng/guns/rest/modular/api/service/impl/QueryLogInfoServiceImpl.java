package com.stylefeng.guns.rest.modular.api.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.rest.common.persistence.dao.QueryLogInfoMapper;
import com.stylefeng.guns.rest.common.persistence.model.QueryLogInfo;
import com.stylefeng.guns.rest.modular.api.service.IQueryLogInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-03-08
 */
@Service
public class QueryLogInfoServiceImpl extends ServiceImpl<QueryLogInfoMapper, QueryLogInfo> implements IQueryLogInfoService {

}
