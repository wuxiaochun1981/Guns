package com.stylefeng.guns.modular.queryLog.service.impl;

import com.stylefeng.guns.common.persistence.model.QueryLogInfo;
import com.stylefeng.guns.common.persistence.dao.QueryLogInfoMapper;
import com.stylefeng.guns.modular.queryLog.service.IQueryLogInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
