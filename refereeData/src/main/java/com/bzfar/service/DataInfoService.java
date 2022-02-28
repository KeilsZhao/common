package com.bzfar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bzfar.entity.DataInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ethons
 * @since 2020-11-23
 */
public interface DataInfoService extends IService<DataInfo> {

    void insert(DataInfo dataInfo);
}
