package com.bzfar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bzfar.entity.Cpws;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ethons
 * @since 2020-11-23
 */
public interface CpwsService extends IService<Cpws> {

    /**
     * @param id
     * @return 根据ID查询对应的文书
     */
    Cpws queryById(Integer id);

    List<Cpws> queryAll();
}
