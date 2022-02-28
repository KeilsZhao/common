package com.bzfar.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bzfar.entity.DataInfo;
import com.bzfar.exception.DataException;
import com.bzfar.mapper.DataInfoMapper;
import com.bzfar.service.DataInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ethons
 * @since 2020-11-23
 */
@Service
public class DataInfoServiceImpl extends ServiceImpl<DataInfoMapper, DataInfo> implements DataInfoService {

    @Resource
    private DataInfoMapper dataInfoMapper;

    @Override
    public void insert(DataInfo dataInfo) {
        try{
           dataInfoMapper.insert(dataInfo);
        }catch (Exception e){
            throw new DataException(e.getMessage());
        }
    }
}
